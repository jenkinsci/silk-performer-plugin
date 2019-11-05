package com.borland.jenkins.SilkPerformerJenkins;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import com.borland.jenkins.SilkPerformerJenkins.util.OverviewReport;
import com.borland.jenkins.SilkPerformerJenkins.util.SilkPerformerListBuilder;

import hudson.EnvVars;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.remoting.VirtualChannel;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

public class SilkPerformerBuilder extends Builder implements Serializable
{
  private static final long serialVersionUID = -3523263836589149152L;

  public static final DescriptorImplementation DESCRIPTOR = new DescriptorImplementation();

  private final String projectLoc;
  private final String workload;
  private final List<SuccessCriteria> successCriteria;

  @DataBoundConstructor
  public SilkPerformerBuilder(String projectLoc, String workload, List<SuccessCriteria> successCriteria)
  {
    this.projectLoc = projectLoc;
    this.workload = workload;
    this.successCriteria = successCriteria;
  }

  public String getProjectLoc()
  {
    return projectLoc;
  }

  public String getWorkload()
  {
    return workload;
  }

  public List<SuccessCriteria> getSuccessCriteria()
  {
    return successCriteria;
  }

  @Override
  public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException
  {
    Boolean callRet = false;
    VirtualChannel channel = launcher.getChannel();
    if (channel != null)
    {
      PrintStream logger = listener.getLogger();
      String projectFilePath = build.getModuleRoot() + "\\" + projectLoc;

      Path path = Paths.get(projectLoc);
      if (path.isAbsolute())
      {
        projectFilePath = projectLoc;
      }

      try
      {
        EnvVars environment = build.getEnvironment(listener);
        String performerInstallDir = environment.get("SP_HOME", "");
        if (performerInstallDir.equals(""))
        {
          logger.println("SP_HOME not set on node.");
        }

        ExecuteOnNode executeOnNode = new ExecuteOnNode(projectFilePath, listener, performerInstallDir, workload, getSuccessCriteria(), build.getProject().getName());
        callRet = channel.call(executeOnNode);
        if (hasOverviewReport(build.getLogReader()))
        {
          File f = new File(projectFilePath);
          try
          {
            build.addAction(
                new OverviewReport(getOverviewReportHtmlArtifactPath(f.getAbsoluteFile().getParent() + File.separator + ExecuteOnNode.RESULTS_NAME, build.getProject().getName())));
          }
          catch (Exception e)
          {
            logger.println("Could not add overview report action.");
          }
        }
      }
      catch (InterruptedException e)
      {
        System.out.println("Job was canceled - starting cleanup.");
        CleanupNode node = new CleanupNode();
        channel.call(node);
        throw e;
      }
    }
    return callRet;
  }

  private boolean hasOverviewReport(Reader reader)
  {
    try (BufferedReader br = new BufferedReader(reader))
    {
      String sCurrentLine = "";
      while ((sCurrentLine = br.readLine()) != null)
      {
        if (sCurrentLine.contains("Overview report generated."))
        {
          return true;
        }
      }
    }
    catch (IOException e)
    {
    }
    return false;
  }

  private String getOverviewReportHtmlArtifactPath(String resultPath, String projectName)
  {
    Path pPath = Paths.get(resultPath);
    int index = -1;
    for (Path p : pPath)
    {
      index++;
      if (p.toString().equals(projectName))
      {
        break;
      }
    }
    StringBuilder sb = new StringBuilder("artifact");
    sb.append(File.separator).append(pPath.subpath(index + 1, pPath.getNameCount()).toString());
    sb.append(File.separator).append(OverviewReport.OVR_NAME);
    return sb.toString().replaceAll("\\\\", "/");
  }

  @Extension
  public static class DescriptorImplementation extends BuildStepDescriptor<Builder> implements Serializable
  {
    private static final long serialVersionUID = -2208342537237605033L;

    public List<String> getMeasureCategoryList()
    {
      return SilkPerformerListBuilder.getMeasurePointClasses();
    }

    public List<String> getMeasureTypes(String measureCategory)
    {
      return SilkPerformerListBuilder.getMeasureTypes(measureCategory);
    }

    public int getMeasureCategoryId(String measureCategory)
    {
      return SilkPerformerListBuilder.getMeasureCategoryId(measureCategory);
    }

    public int getMeasureTypeId(String measureType)
    {
      return SilkPerformerListBuilder.getMeasureTypeId(measureType);
    }

    public String getMeasureCategoryName(int measureCategoryId)
    {
      return SilkPerformerListBuilder.getMeasureCategoryName(measureCategoryId);
    }

    public String getMeasureTypeName(int measureTypeId)
    {
      return SilkPerformerListBuilder.getMeasureTypeName(measureTypeId);
    }

    public DescriptorImplementation()
    {
      load();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isApplicable(Class<? extends AbstractProject> jobType)
    {
      return true;
    }

    @Override
    public String getDisplayName()
    {
      return "Execute Silk Performer Tests";
    }
  }
}
