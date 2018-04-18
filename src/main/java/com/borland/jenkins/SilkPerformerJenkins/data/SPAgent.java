package com.borland.jenkins.SilkPerformerJenkins.data;

import java.util.ArrayList;
import java.util.List;

public class SPAgent
{
  private String name;
  private List<SPUserType> userTypes = new ArrayList<SPUserType>();

  public SPAgent(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return this.name;
  }

  public List<SPUserType> getUserTypes()
  {
    return userTypes;
  }

  public void addUserType(SPUserType userType)
  {
    userTypes.add(userType);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    sb.append("Agent: ").append(name).append("\n");
    for (SPUserType ut : userTypes)
    {
      sb.append(ut.toString());
    }

    return sb.toString();
  }
}
