package com.borland.jenkins.SilkPerformerJenkins.data;

import java.util.ArrayList;
import java.util.List;

public class SPUserType
{

  private String name;
  private String profile;
  private String script;
  private List<SPMeasure> measures = new ArrayList<SPMeasure>();

  public SPUserType(String name, String profile, String script)
  {
    this.name = name;
    this.profile = profile;
    this.script = script;
  }

  public String getName()
  {
    return name;
  }

  public String getProfile()
  {
    return profile;
  }

  public String getScript()
  {
    return script;
  }

  public List<SPMeasure> getMeasure()
  {
    return measures;
  }

  public void addMeasure(SPMeasure measure)
  {
    measures.add(measure);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    sb.append(toStringLog()).append("\n");
    for (SPMeasure m : measures)
    {
      sb.append("\t\t").append(m.toString()).append("\n\n");
    }

    return sb.toString();
  }

  public String toStringLog()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("\tUserType: ").append(getUserType());
    return sb.toString();
  }

  public String getUserType()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(script).append("/").append(name).append("/").append(profile);
    return sb.toString();
  }
}
