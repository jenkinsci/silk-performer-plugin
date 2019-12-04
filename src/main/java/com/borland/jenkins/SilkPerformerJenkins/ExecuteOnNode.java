package com.borland.jenkins.SilkPerformerJenkins;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.borland.jenkins.SilkPerformerJenkins.data.SPAgent;
import com.borland.jenkins.SilkPerformerJenkins.data.SPUserType;
import com.borland.jenkins.SilkPerformerJenkins.util.OverviewReport;
import com.borland.jenkins.SilkPerformerJenkins.util.SilkPerformerTestManager;
import com.borland.jenkins.SilkPerformerJenkins.util.SystemUtils;
import com.borland.jenkins.SilkPerformerJenkins.util.XMLProjectReader;
import com.borland.jenkins.SilkPerformerJenkins.util.XMLReader;

import hudson.model.BuildListener;
import jenkins.security.MasterToSlaveCallable;

public class ExecuteOnNode extends MasterToSlaveCallable<Boolean, IOException> implements Serializable
{
  private static final long serialVersionUID = -7451856820879041390L;

  public static final String RESULTS_NAME = "result" + File.separator;

  private String performerInstallDir;
  BuildListener listener;
  String projectFilePath;
  String projectPath;
  private List<SuccessCriteria> successCriteria;
  String workload;
  boolean usePerformanceLevels;

  public ExecuteOnNode(String projectFilePath, BuildListener listener, String performerInstallDir, String workload, List<SuccessCriteria> successCriteria, String projectName)
  {

    this.listener = listener;
    this.performerInstallDir = performerInstallDir;
    this.projectFilePath = projectFilePath;
    this.workload = workload;
    this.successCriteria = successCriteria;
    usePerformanceLevels = (successCriteria == null);
  }

  @Override
  public Boolean call() throws IOException
  {
    SystemUtils.initSystem(performerInstallDir, listener);
    SilkPerformerTestManager sptm;
    XMLReader spxml;
    listener.getLogger().println("Initializing the load tests.");
    File f = new File(projectFilePath);
    projectPath = f.getAbsoluteFile().getParent();
    String resultPath = projectPath + File.separator + ExecuteOnNode.RESULTS_NAME;
    try
    {
      listener.getLogger().println("");
      listener.getLogger().println("USING THE FOLLOWING SETTINGS");
      listener.getLogger().println("Project file path: " + projectFilePath);
      listener.getLogger().println("Results path: " + resultPath);
      listener.getLogger().println("Silk Performer installation directory: " + performerInstallDir);

      sptm = new SilkPerformerTestManager(projectFilePath, resultPath, performerInstallDir, workload);
      if (workload.length() > 0)
      {
        listener.getLogger().println("Running workload: " + workload);
      }
      else
      {
        workload = sptm.getActiveWorkload();
        listener.getLogger().println("Using default workload: " + workload);
      }
      listener.getLogger().println("");
      listener.getLogger().println("Agent messages:");
      sptm.startTheLoadTest(listener.getLogger());
      listener.getLogger().println("");
      listener.getLogger().println("Completed load test.");
      listener.getLogger().println("");

      spxml = new XMLReader();
      List<SPAgent> spAgentList;
      if (usePerformanceLevels)
      {
        spAgentList = spxml.readResults(resultPath + "detailedReport.xml", true, listener.getLogger());
        successCriteria = createSuccessCriteraFromPerformanceLevels(projectPath, workload, listener.getLogger());
        if (successCriteria == null || successCriteria.isEmpty())
        {
          listener.getLogger().print("Cannot find the Performance Level file " + projectPath + "\\PerformanceLevels\\" + workload + ".pls.");
          listener.getLogger().println("");
          listener.getLogger().println("Make sure that the file is available in your source control system or define other success criteria.");
        }
      }
      else
      {
        spAgentList = spxml.readResults(resultPath + "detailedReport.xml", false, listener.getLogger());
      }
      if (spAgentList == null || spAgentList.isEmpty())
      {
        listener.getLogger().println("Cannot read detailedReport.xml!");
        return false;
      }
      // returning false will make the build a failure
      listener.getLogger().println("");
      generateOverViewReport(listener, performerInstallDir, resultPath);

      generateProjectInfo(spAgentList);

      if (successCriteria == null || successCriteria.isEmpty())
      {
        listener.getLogger().println("WARNING: No success criteria defined or Performance Level file not found.");
        listener.getLogger().println("Falling back to default success criteria. Checking for execution errors.");
        listener.getLogger().println("");
        successCriteria = new ArrayList<>();
        successCriteria.add(new SuccessCriteria("All", "Summary General", "Errors", "All", "Count All", "=", "0"));
      }
      else
      {
        listener.getLogger().println("Processing results using " + (usePerformanceLevels ? "project's performance levels." : "Jenkins' success criteria."));
      }

      return spxml.processResults(spAgentList, successCriteria, listener);
    }
    catch (Exception e)
    {
      e.printStackTrace(listener.getLogger());
    }
    return false;
  }

  private void generateOverViewReport(BuildListener listener, String performerInstallDir, String resultPath)
  {
    if (Paths.get(performerInstallDir + File.separator + "perfExp.exe").toFile().exists())
    {
      listener.getLogger().println("Creating overview report.");

      final String COMMANDFILE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>%n" + "<PerfExpCommandFile NoGui=\"True\" LogFile=\"%s\">%n" + " <OverviewReport%n"
          + "      File=\"%s\"%n" + "      Export=\"%s\"%n" + "  />%n" + "   <Command>SINGLEINSTANCE</Command>%n" + "   <Command>ACTION:OVERVIEWREPORT</Command>  %n"
          + "   <Command>EXIT</Command>%n" + "</PerfExpCommandFile>";
      final String COMMANDLINE = "\"%sperfExp.exe\" /COMMANDFILE:\"%scommandGenOVR.txt\"";

      try
      {
        Thread.sleep(2000);
        Stream<Path> find = Files.find(Paths.get(resultPath).toAbsolutePath(), 10, (path, basicFileAttributes) -> path.toFile().getName().matches("m@.*.tsd"));
        Optional<Path> findFirst = find.findFirst();
        find.close();
        if (findFirst.isPresent())
        {
          Path tsdFilePath = findFirst.get();
          Path ovrFilePath = Paths.get(resultPath, OverviewReport.OVR_NAME);
          Path perfExpLogFilePath = Paths.get(resultPath, "perfExpLog.log");

          // Create OverviewReport file's parent directory, if not exists
          Path ovrDir = ovrFilePath.getParent();
          if (ovrDir != null && !ovrDir.toFile().exists())
          {
            Files.createDirectory(ovrDir);
          }

          String commandFile = String.format(COMMANDFILE, perfExpLogFilePath.toFile().getAbsolutePath(), tsdFilePath.toFile().getAbsolutePath(),
              ovrFilePath.toFile().getAbsolutePath());
          Files.write(Paths.get(resultPath, "commandGenOVR.txt"), commandFile.getBytes(StandardCharsets.UTF_8));

          String commandline = String.format(COMMANDLINE, performerInstallDir + File.separator, resultPath);
          Process p = Runtime.getRuntime().exec(commandline);
          while (p.isAlive())
          {
            Thread.sleep(1000);
          }
          listener.getLogger().println("Overview report generated.");
        }
        else
        {
          listener.getLogger().println("Could not generate Overview report.");
        }
      }
      catch (IOException e)
      {
        listener.error(e.toString());
        e.printStackTrace(listener.getLogger());
      }
      catch (InterruptedException e)
      {
        e.printStackTrace(listener.getLogger());
      }
    }
    else
    {
      listener.error("Cannot generate overview report due to missing perfExp.exe.");
    }
  }

  private void generateProjectInfo(List<SPAgent> spAgentList)
  {
    String separator = "%n#######################################################%n";

    listener.getLogger().println(separator);
    XMLProjectReader.printWorkloads(projectFilePath, listener.getLogger());
    if (!spAgentList.isEmpty())
    {
      for (SPUserType ut : spAgentList.get(0).getUserTypes())
      {
        listener.getLogger().println(ut.toPrint());
      }
    }
    listener.getLogger().println(separator);
  }

  public List<SuccessCriteria> createSuccessCriteraFromPerformanceLevels(String projectPath, String workload, PrintStream logger)
  {
    String performanceLevelFile = projectPath + "\\PerformanceLevels\\" + workload + ".pls";
    ArrayList<SuccessCriteria> criteriaList = new ArrayList<>();

    Document doc = null;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try
    {
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse(new java.io.File(performanceLevelFile));
    }
    catch (Exception e)
    {
      // e.printStackTrace(logger);
      return criteriaList;
    }

    NodeList perfLevelCategories = doc.getElementsByTagName("PerformanceLevelCategories");
    if (perfLevelCategories.getLength() == 0)
    {
      logger.println("Categories size: 0");
      return criteriaList;
    }

    Element categoryListNode = (Element) perfLevelCategories.item(0);
    NodeList categories = categoryListNode.getElementsByTagName("Category");
    for (int i = 0; i < categories.getLength(); i++)
    {
      Element category = (Element) categories.item(i);

      NodeList conditionList = category.getElementsByTagName("Condition");
      for (int j = 0; j < conditionList.getLength(); j++)
      {
        Element condition = (Element) conditionList.item(j);
        System.out.println(String.format("Node %s, %s, %s", condition.getNodeName(), condition.getAttribute("value"), condition.getAttribute("type")));
        if (!condition.getAttribute("type").equalsIgnoreCase("enone"))
        {
          Element appliesTo = (Element) category.getElementsByTagName("AppliesTo").item(0);
          if (appliesTo != null)
          {
            NodeList measureList = appliesTo.getElementsByTagName("Measure");

            for (int k = 0; k < measureList.getLength(); k++)
            {
              Element measure = (Element) measureList.item(k);
              String userType = measure.getAttribute("vuScript") + "/" + measure.getAttribute("vuName") + "/" + measure.getAttribute("vuProfile");
              listener.getLogger().println("creating success criterion" + "measurename " + measure.getAttribute("name") + " " + measure.getAttribute("measureType") + " class "
                  + measure.getAttribute("measureClass"));
              SuccessCriteria c = new SuccessCriteria(userType, Integer.parseInt(measure.getAttribute("measureClass")), Integer.parseInt(measure.getAttribute("measureType")),
                  measure.getAttribute("name"), condition.getAttribute("type"), "<=", condition.getAttribute("value"));
              criteriaList.add(c);
            }
          }
        }
      }
    }
    return criteriaList;
  }
}
