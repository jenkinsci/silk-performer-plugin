package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.PrintStream;
import java.util.Iterator;

import com.segue.em.Ltc;
import com.segue.em.Projectfile;
import com.segue.em.SGExecutionManager;
import com.segue.em.projectfile.ProfileBooleanProperty;
import com.segue.em.projectfile.TransactionInfo;
import com.segue.em.projectfile.UserType;
import com.segue.em.remote.ISGExecutionManager;

public class SilkPerformerTestManager
{

  private final String projectLoc;
  private final String resultDir;
  private final String installationDir;
  private final String workload;

  public SilkPerformerTestManager(String projectLoc, String resultDir, String installationDir, String workload) throws Exception
  {
    this.projectLoc = projectLoc;
    this.resultDir = resultDir;
    this.installationDir = installationDir;
    this.workload = workload;
  }

  public String getActiveWorkload() throws Exception
  {
    Projectfile prjf = SGExecutionManager.openProject(projectLoc);
    String activeWorkload = prjf.getActiveWorkload();
    prjf.close();
    return activeWorkload;
  }

  public void startTheLoadTest(PrintStream logger) throws Exception
  {
    String configLoc = installationDir + "/sgExecManagerLtc.xml";
    Projectfile prjf = SGExecutionManager.openProject(projectLoc);
    prjf.setProfileBooleanProperty(new ProfileBooleanProperty(prjf, "PROFSetting_RESULTS_Option_ReportFiles", true));
    prjf.setResultsDir(resultDir);
    if (workload.length() > 0)
    {
      prjf.setActiveWorkload(workload);
    }
    prjf.save();
    prjf.close();

    SGExecutionManager.setMessagingMode(ISGExecutionManager.FLAG_LTC_MESSAGING_LOCALPUMP);
    Ltc ltc = SGExecutionManager.createController(configLoc);

    ltc.addEventListener(new JenkinsSPListener(logger));
    ltc.setDeployBuildOption(ISGExecutionManager.BUILD_METHOD_REBUILD);
    ltc.deployProject(projectLoc);
    ltc.setVuOutputOptions(SGExecutionManager.OPT_DISPLAY_ALL_MSG);
    ltc.start(-1);
    ltc.undeploy();
    ltc.generateOverviewReport(OverviewReport.OVR_NAME);
    ltc.destroy();
    SGExecutionManager.destroyController(ltc);
  }

  public String getInformation() throws Exception
  {
    StringBuilder result = new StringBuilder();
    Projectfile prjf = SGExecutionManager.openProject(projectLoc);
    String namewl = prjf.getActiveWorkload();
    prjf.setCurrentWorkload(namewl);
    UserType itut = prjf.getFirstUserType();
    while (itut != null)
    {
      result.append(itut.getScriptName());
      result.append("/");
      result.append(itut.getUsergroupName());
      result.append("/");
      result.append(itut.getProfileName());
      result.append("\n");
      itut = prjf.getNextUserType();
    }
    Iterator<TransactionInfo> itti = prjf.getTransactionInfo();
    while (itti.hasNext())
    {
      TransactionInfo ti = itti.next();
      result.append(ti.getTransactionName());
      result.append("\n");
    }
    prjf.close();
    return result.toString();
  }
}
