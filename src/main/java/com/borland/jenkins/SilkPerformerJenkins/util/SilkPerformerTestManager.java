package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.PrintStream;
import java.util.Iterator;

import com.borland.jenkins.SilkPerformerJenkins.wrapper.ISGExecutionManagerWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.LtcWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.ProjectfileWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.SGExecutionManagerWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.TransactionInfoWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.UserTypeWrapper;

public class SilkPerformerTestManager
{

  private final String projectLoc;
  private final String resultDir;
  private final String installationDir;
  private final String workload;
  private final PrintStream logger;

  public SilkPerformerTestManager(String projectLoc, String resultDir, String installationDir, String workload, PrintStream logger) throws Exception
  {
    this.projectLoc = projectLoc;
    this.resultDir = resultDir;
    this.installationDir = installationDir;
    this.workload = workload;
    this.logger = logger;
  }

  public String getActiveWorkload() throws Exception
  {
    Object prjf = SGExecutionManagerWrapper.openProject(projectLoc);
    String activeWorkload = ProjectfileWrapper.getActiveWorkload(prjf);
    ProjectfileWrapper.close(prjf);
    return activeWorkload;
  }

  public void startTheLoadTest() throws Exception
  {
    String configLoc = installationDir + "/sgExecManagerLtc.xml";
    Object prjf = SGExecutionManagerWrapper.openProject(projectLoc);
    ProjectfileWrapper.setProfileBooleanProperty(prjf, "PROFSetting_RESULTS_Option_ReportFiles", true);
    ProjectfileWrapper.setResultsDir(prjf, resultDir);
    if (workload.length() > 0)
    {
      ProjectfileWrapper.setActiveWorkload(prjf, workload);
    }
    ProjectfileWrapper.save(prjf);
    ProjectfileWrapper.close(prjf);

    SGExecutionManagerWrapper.setMessagingMode(ISGExecutionManagerWrapper.getAttribute("FLAG_LTC_MESSAGING_LOCALPUMP"));
    Object ltc = SGExecutionManagerWrapper.createController(configLoc);

    LtcWrapper.addEventListener(ltc, JenkinsSPListener.getListener(logger));
    LtcWrapper.setDeployBuildOption(ltc, ISGExecutionManagerWrapper.getAttribute("BUILD_METHOD_REBUILD"));
    LtcWrapper.deployProject(ltc, projectLoc);
    LtcWrapper.setVuOutputOptions(ltc, SGExecutionManagerWrapper.getAttribute("OPT_DISPLAY_ALL_MSG"));
    LtcWrapper.start(ltc, -1);
    LtcWrapper.undeploy(ltc);
    LtcWrapper.destroy(ltc);
    SGExecutionManagerWrapper.destroyController(ltc);
  }

  public String getInformation() throws Exception
  {
    StringBuilder result = new StringBuilder();
    Object prjf = SGExecutionManagerWrapper.openProject(projectLoc);
    String namewl = ProjectfileWrapper.getActiveWorkload(prjf);
    ProjectfileWrapper.setCurrentWorkload(prjf, namewl);
    Object itut = ProjectfileWrapper.getFirstUserType(prjf);
    while (itut != null)
    {
      result.append(UserTypeWrapper.getScriptName(itut));
      result.append("/");
      result.append(UserTypeWrapper.getUsergroupName(itut));
      result.append("/");
      result.append(UserTypeWrapper.getProfileName(itut));
      result.append("\n");
      itut = ProjectfileWrapper.getNextUserType(prjf);
    }
    Iterator<Object> itti = ProjectfileWrapper.getTransactionInfo(prjf);
    while (itti.hasNext())
    {
      Object ti = itti.next();
      result.append(TransactionInfoWrapper.getTransactionName(ti));
      result.append("\n");
    }
    ProjectfileWrapper.close(prjf);
    return result.toString();
  }
}
