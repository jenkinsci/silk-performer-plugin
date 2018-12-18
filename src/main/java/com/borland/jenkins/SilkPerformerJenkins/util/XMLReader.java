package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.borland.jenkins.SilkPerformerJenkins.SuccessCriteria;
import com.borland.jenkins.SilkPerformerJenkins.data.SPAgent;
import com.borland.jenkins.SilkPerformerJenkins.data.SPMeasure;
import com.borland.jenkins.SilkPerformerJenkins.data.SPUserType;

import hudson.model.BuildListener;

public class XMLReader implements Serializable
{
  private static final long serialVersionUID = 592236199076765067L;

  private String getElementValue(Element e, String tagName)
  {
    String ret = "";
    NodeList nl = e.getElementsByTagName(tagName);
    if (nl != null)
    {
      Node n = nl.item(0);
      if (n != null)
      {
        ret = n.getTextContent();
      }
    }
    return ret;
  }

  private double round(double num, int places)
  {
    if (places < 0)
    {
      throw new IllegalArgumentException();
    }

    long factor = (long) Math.pow(10, places);
    num = num * factor;
    long tmp = Math.round(num);
    return (double) tmp / factor;
  }

  private boolean expressionReader(Double val1, String op, String val2)
  {
    if (op.equals("<"))
    {
      return (val1 < Double.valueOf(val2));
    }
    else if (op.equals(">"))
    {
      return (val1 > Double.valueOf(val2));
    }
    else if (op.equals("<="))
    {
      return (val1 <= Double.valueOf(val2));
    }
    else if (op.equals(">="))
    {
      return (val1 >= Double.valueOf(val2));
    }
    else if (op.equals("="))
    {
      return (val1.equals(Double.valueOf(val2)));
    }
    return false;
  }

  public boolean processResults(List<SPAgent> spAgentList, List<SuccessCriteria> successCriteria, BuildListener listener)
  {
    boolean ret = true;
    if (successCriteria == null)
    {
      listener.getLogger().println("No success criteria defined.");
      return true;
    }
    if (spAgentList == null || spAgentList.isEmpty())
    {
      listener.getLogger().println("No agent list defined.");
      return false;
    }
    for (SuccessCriteria sc : successCriteria)
    {
      listener.getLogger().println("checking " + sc.toStringLog() + " in \"" + sc.getUserType() + "\" user type");
      boolean bRetAgent = true;
      for (SPAgent ag : spAgentList)
      {
        boolean bUserTypeFound = false;
        for (SPUserType ut : ag.getUserTypes())
        {
          if ((sc.getUserType().equalsIgnoreCase(ut.getUserType()) || sc.getUserType().equalsIgnoreCase("all")))
          {
            bUserTypeFound = true;
            bRetAgent = bRetAgent && checkMeasures(sc, ut, listener);
          }
        }
        if (!bUserTypeFound)
        {
          listener.getLogger().println("\tFailed! User Type \"" + sc.getUserType() + "\" not found");
          bRetAgent = false;
        }
      }
      if (bRetAgent)
      {
        listener.getLogger().println("\tSucceeded");
      }
      ret = ret && bRetAgent;
    }
    return ret;
  }

  private boolean checkMeasures(SuccessCriteria sc, SPUserType ut, BuildListener listener)
  {
    boolean bMeasureFound = false;
    boolean bSuccessCriteriaFailed = false;
    for (SPMeasure m : ut.getMeasureList())
    {
      if (sc.isSelectedMeasure(m))
      {
        bMeasureFound = true;
        if (sc.getValueType().equals("Minimum Value"))
        {
          if (!expressionReader(m.getMin(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getMin()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("Maximum Value"))
        {
          if (!expressionReader(m.getMax(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getMax()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("Count All"))
        {
          if (!expressionReader(Double.valueOf(m.getCount()), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, Double.valueOf(m.getCount())));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("Average Value") || sc.getValueType().equals("eAverage"))
        {
          if (!expressionReader(m.getAvg(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getAvg()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("ePercentile50"))
        {
          if (!expressionReader(m.getPercentile50(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getPercentile50()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("ePercentile90"))
        {
          if (!expressionReader(m.getPercentile90(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getPercentile90()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("ePercentile95"))
        {
          if (!expressionReader(m.getPercentile95(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getPercentile95()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else if (sc.getValueType().equals("ePercentile99"))
        {
          if (!expressionReader(m.getPercentile99(), sc.getOperatorType(), sc.getChosenValue()))
          {
            listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getPercentile99()));
            bSuccessCriteriaFailed = true;
            break;
          }
        }
        else
        {
          listener.getLogger().println("Value typeof the success criterion not found.");
        }
        break;
      }
    }

    if (!bMeasureFound)
    {
      listener.getLogger().println("\tFailed! Measure not found for : " + ut.toStringLog());
    }

    return bMeasureFound && !bSuccessCriteriaFailed;
  }

  private String formatSuccessCriteria(SPUserType ut, SuccessCriteria sc, Double dFoundValue)
  {
    StringBuilder sb = new StringBuilder("\tFailed!");
    sb.append(sc.toString());
    sb.append("\n\tFound value is : ").append(dFoundValue);
    return sb.toString();
  }

  public String printResults(List<SPAgent> agentsList)
  {
    StringBuilder result = new StringBuilder();

    result.append("Results: \n");
    for (SPAgent ag : agentsList)
    {
      result.append(ag.toString());
    }

    return result.toString();
  }

  public List<SPAgent> readResults(String xmlFile, boolean readPercentileData, PrintStream logger)
  {
    List<SPAgent> spAgentList = new ArrayList<>();

    Document doc = null;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try
    {
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse(new java.io.File(xmlFile));
    }
    catch (Exception e)
    {
      e.printStackTrace(logger);
      return spAgentList;
    }

    NodeList agentListNodeList = doc.getElementsByTagName("AgentList");
    if (agentListNodeList.getLength() == 0)
    {
      return spAgentList;
    }
    Element agentListNode = (Element) agentListNodeList.item(0);

    NodeList agentNodeList = agentListNode.getElementsByTagName("Agent");
    for (int i = 0; i < agentNodeList.getLength(); i++)
    {
      Element agentNode = (Element) agentNodeList.item(i);
      SPAgent spAgent = new SPAgent(agentNode.getAttribute("name"));
      spAgentList.add(spAgent);

      NodeList userTypeNodeList = agentNode.getElementsByTagName("UserType");
      for (int j = 0; j < userTypeNodeList.getLength(); j++)
      {
        Element userTypeNode = (Element) userTypeNodeList.item(j);
        SPUserType spUserType = new SPUserType(userTypeNode.getAttribute("name"), userTypeNode.getAttribute("profile"), userTypeNode.getAttribute("script"));
        spAgent.addUserType(spUserType);

        NodeList measureNodeList = userTypeNode.getElementsByTagName("Measure");
        for (int k = 0; k < measureNodeList.getLength(); k++)
        {
          Element measureNode = (Element) measureNodeList.item(k);
          SPMeasure spMeasure = new SPMeasure(measureNode.getAttribute("name"), Integer.parseInt(measureNode.getAttribute("measureClass")),
              Integer.parseInt(measureNode.getAttribute("measureType")));
          spUserType.addMeasure(spMeasure);

          Element e = measureNode;

          spMeasure.setDisplayType(getElementValue(e, "DisplayType"));
          spMeasure.setDisplayClass(getElementValue(e, "Class"));
          spMeasure.setMin(round(Double.parseDouble(getElementValue(e, "Min")), 2));
          spMeasure.setMax(round(Double.parseDouble(getElementValue(e, "Max")), 2));
          spMeasure.setSum(round(Double.parseDouble(getElementValue(e, "Sum")), 2));
          spMeasure.setCountMeasured(Integer.parseInt(getElementValue(e, "CountMeasured")));
          spMeasure.setUnit(getElementValue(e, "Unit"));
          spMeasure.setCount(Integer.parseInt(getElementValue(e, "CountAll")));
          spMeasure.setAvg(round((spMeasure.getSum() / spMeasure.getCountMeasured()), 2));

          // only necessary when evaluating performance levels.
          if (readPercentileData)
          {
            NodeList percentileDataList = measureNode.getElementsByTagName("PercentileData");
            if (percentileDataList != null)
            {
              Element item = (Element) percentileDataList.item(0);
              if (item != null)
              {
                NodeList markerList = item.getElementsByTagName("MarkerList");
                Element marker = (Element) markerList.item(0);
                NodeList elementsByTagName = marker.getElementsByTagName("Marker");
                Element percentile50 = (Element) elementsByTagName.item(50).getChildNodes().item(1);
                Element percentile90 = (Element) elementsByTagName.item(90).getChildNodes().item(1);
                Element percentile95 = (Element) elementsByTagName.item(95).getChildNodes().item(1);
                Element percentile99 = (Element) elementsByTagName.item(99).getChildNodes().item(1);

                spMeasure.setPercentile50(Double.valueOf(percentile50.getTextContent()));
                spMeasure.setPercentile90(Double.valueOf(percentile90.getTextContent()));
                spMeasure.setPercentile95(Double.valueOf(percentile95.getTextContent()));
                spMeasure.setPercentile99(Double.valueOf(percentile99.getTextContent()));
              }
            }
          }
        }
      }
    }
    return spAgentList;
  }
}
