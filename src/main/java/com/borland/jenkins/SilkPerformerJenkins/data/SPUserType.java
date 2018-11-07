package com.borland.jenkins.SilkPerformerJenkins.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SPUserType
{

  private String name;
  private String profile;
  private String script;
  private List<SPMeasure> measures = new ArrayList<>();

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

  public List<SPMeasure> getMeasureList()
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
    return new StringBuilder().append("UserType - ").append(getUserType()).toString();
  }

  public String getUserType()
  {
    return new StringBuilder().append(script).append("/").append(name).append("/").append(profile).toString();
  }

  public String toPrint()
  {
    StringBuilder sb = new StringBuilder();
    Map<String, MeasurePair> mapClass = new TreeMap<>();
    for (SPMeasure m : measures)
    {
      String measureClass = m.getDisplayClass();
      String measureName = m.getName();
      String measureType = m.getDisplayType();

      if (measureClass.equals("Summary Report"))
      {
        String temp = measureClass;
        measureClass = measureName;
        measureName = temp;
      }

      if (!mapClass.containsKey(measureClass))
      {
        mapClass.put(measureClass, new MeasurePair());
      }
      MeasurePair pair = mapClass.get(measureClass);
      pair.hsMeasureName.add(measureName);
      pair.hsMeasureType.add(measureType);
    }

    sb.append("|--------------------------------------------------------------------------------------------------|\n");
    sb.append(String.format("|  %-96s|%s", "UserType - " + getUserType(), "\n"));
    sb.append("|--------------------------------|--------------------------------|--------------------------------|\n");
    sb.append("|        Measure Category        |          Measure Type          |          Measure Name          |\n");
    sb.append("|--------------------------------|--------------------------------|--------------------------------|\n");
    for (Entry<String, MeasurePair> entryClass : mapClass.entrySet())
    {
      String measureCategory = entryClass.getKey();
      MeasurePair pair = entryClass.getValue();
      Iterator<String> itMeasureType = pair.hsMeasureType.iterator();
      Iterator<String> itMeasureName = pair.hsMeasureName.iterator();
      while (itMeasureType.hasNext() || itMeasureName.hasNext())
      {
        sb.append(String.format("|  %-30s|  %-30s|  %-30s|%s", measureCategory, itMeasureType.hasNext() ? itMeasureType.next() : "",
            itMeasureName.hasNext() ? itMeasureName.next() : "", "\n"));

        measureCategory = "";
      }
      sb.append("|--------------------------------------------------------------------------------------------------|\n");
    }

    return sb.toString();
  }

  private class MeasurePair
  {
    private Set<String> hsMeasureType = new HashSet<>();
    private Set<String> hsMeasureName = new HashSet<>();
  }
}
