package com.segue.em;

import java.io.Serializable;

import com.segue.em.ltc.Listener;

public class Ltc extends SgemCapableObject implements Serializable
{
  public void addEventListener(Listener listener)
  {
  }

  public void setDeployBuildOption(int delployOption)
  {
  }

  public void deployProject(String prjFileName)
  {
  }

  public int start(int iTimeout)
  {
    return 0;
  }

  public void undeploy()
  {
  }

  public boolean generateOverviewReport(String sOVRFileName)
  {
    return false;
  }

  public synchronized void destroy()
  {
  }

  public void setVuOutputOptions(int uOptions)
  {
  }
}
