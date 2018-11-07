package com.borland.jenkins.SilkPerformerJenkins.util;

import java.net.URISyntaxException;

import hudson.model.Action;

public class OverviewReport implements Action
{
  public static final String OVR_NAME = "OverviewReport\\OverviewReport.html";

  private String urlName;

  public OverviewReport(String urlName)
  {
    this.urlName = urlName.replaceAll(" ", "%20");
  }

  @Override
  public String getIconFileName()
  {
    return "plugin/SilkPerformerJenkins/images/24x24/OverviewReport_24x24.png";
  }

  @Override
  public String getDisplayName()
  {
    return "View Overview Report";
  }

  @Override
  public String getUrlName()
  {
    return urlName;
  }

}
