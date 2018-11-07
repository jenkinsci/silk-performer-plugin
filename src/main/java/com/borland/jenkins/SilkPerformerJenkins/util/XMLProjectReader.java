package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.PrintStream;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLProjectReader implements Serializable
{
  private static final long serialVersionUID = 1931733484257883210L;

  public static void printWorkloads(String xmlProjectFile, PrintStream logger)
  {
    Document doc;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try
    {
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse(new java.io.File(xmlProjectFile));
    }
    catch (Exception e)
    {
      e.printStackTrace(logger);
      return;
    }

    NodeList workloadListNodeList = doc.getElementsByTagName("WorkloadList");
    if (workloadListNodeList.getLength() == 0)
    {
      logger.println("No workload defined.");
      return;
    }

    StringBuilder sb = new StringBuilder("List of workloads:\n");
    Element workloadListNode = (Element) workloadListNodeList.item(0);
    NodeList workloadNodeList = workloadListNode.getElementsByTagName("Workload");
    for (int i = 0; i < workloadNodeList.getLength(); i++)
    {
      Element workloadNode = (Element) workloadNodeList.item(i);
      sb.append("\t");
      sb.append(workloadNode.getAttribute("name"));
      if (workloadNode.getAttribute("active").equalsIgnoreCase("true"))
      {
        sb.append("(Active)");
      }
      sb.append("\n");
    }
    logger.println(sb.toString());
  }
}
