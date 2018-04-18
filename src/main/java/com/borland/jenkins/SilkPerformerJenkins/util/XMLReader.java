package com.borland.jenkins.SilkPerformerJenkins.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.borland.jenkins.SilkPerformerJenkins.SuccessCriteria;
import com.borland.jenkins.SilkPerformerJenkins.data.SPAgent;
import com.borland.jenkins.SilkPerformerJenkins.data.SPMeasure;
import com.borland.jenkins.SilkPerformerJenkins.data.SPUserType;

import hudson.model.BuildListener;

public class XMLReader
{

  public List<SPAgent> readResults(String xmlFileName) throws XMLStreamException
  {
    List<SPAgent> spAgentList = new ArrayList<SPAgent>();

    Document doc = null;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try
    {
      // Using factory get an instance of document builder
      DocumentBuilder db = dbf.newDocumentBuilder();
      // parse using builder to get DOM representation of the XML file
      doc = db.parse(xmlFileName);
    }
    catch (Exception e)
    {
      e.printStackTrace();
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

          Element e = (Element) measureNode;

          spMeasure.setDisplayType(getElementValue(e, "DisplayType"));
          spMeasure.setMin(round(Double.parseDouble(getElementValue(e, "Min")), 2));
          spMeasure.setMax(round(Double.parseDouble(getElementValue(e, "Max")), 2));
          spMeasure.setSum(round(Double.parseDouble(getElementValue(e, "Sum")), 2));
          spMeasure.setCountMeasured(Integer.parseInt(getElementValue(e, "CountMeasured")));
          spMeasure.setUnit(getElementValue(e, "Unit"));
          spMeasure.setCount(Integer.parseInt(getElementValue(e, "CountAll")));
          spMeasure.setAvg(round((spMeasure.getSum() / spMeasure.getCountMeasured()), 2));
        }
      }
    }

    return spAgentList;
  }

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
    if (successCriteria == null)
    {
      return true;
    }
    if (spAgentList == null || spAgentList.size() == 0)
    {
      return false;
    }
    for (SuccessCriteria sc : successCriteria)
    {
      for (SPAgent ag : spAgentList)
      {
        for (SPUserType ut : ag.getUserTypes())
        {
          if (sc.getUserType().equals(ut.getUserType()) || sc.getUserType().equals("all"))
          {
            if (!checkMeasures(sc, ut, listener))
            {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  private boolean checkMeasures(SuccessCriteria sc, SPUserType ut, BuildListener listener)
  {
    boolean bMeasureFound = false;
    for (SPMeasure m : ut.getMeasure())
    {
      if (sc.getTransactionName().equals("all") || sc.getTransactionName().equals(m.getName()))
      {
        if (sc.isSelectedMeasure(m, listener))
        {
          bMeasureFound = true;
          if (sc.getValueType().equals("Minimum Value"))
          {
            if (!expressionReader(m.getMin(), sc.getOperatorType(), sc.getChosenValue()))
            {
              listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getMin()));
              return false;
            }
          }
          else if (sc.getValueType().equals("Maximum Value"))
          {
            if (!expressionReader(m.getMax(), sc.getOperatorType(), sc.getChosenValue()))
            {
              listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getMax()));
              return false;
            }
          }
          else if (sc.getValueType().equals("Count All"))
          {
            if (!expressionReader(Double.valueOf(m.getCount()), sc.getOperatorType(), sc.getChosenValue()))
            {
              listener.getLogger().println(formatSuccessCriteria(ut, sc, Double.valueOf(m.getCount())));
              return false;
            }
          }
          else if (sc.getValueType().equals("Average Value"))
          {
            if (!expressionReader(m.getAvg(), sc.getOperatorType(), sc.getChosenValue()))
            {
              listener.getLogger().println(formatSuccessCriteria(ut, sc, m.getAvg()));
              return false;
            }
          }
          return true;
        }
      }
    }

    if (!bMeasureFound)
    {
      StringBuilder sb = new StringBuilder("Measure not found for :\n");
      sb.append(ut.toStringLog());
      sb.append(sc.toStringLog());
      listener.getLogger().println(sb.toString());
    }

    return bMeasureFound;
  }

  private String formatSuccessCriteria(SPUserType ut, SuccessCriteria sc, Double dFoundValue)
  {
    StringBuilder sb = new StringBuilder("Success Criteria failed!\n");
    sb.append(sc.toString());
    sb.append("\nFound value is : ").append(dFoundValue);
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
}
