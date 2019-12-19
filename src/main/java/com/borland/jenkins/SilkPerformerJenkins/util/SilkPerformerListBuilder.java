package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.borland.jenkins.SilkPerformerJenkins.util.SilkPerformerMeasuresList.SilkPerformerMeasure;

public class SilkPerformerListBuilder implements Serializable
{
  private static final long serialVersionUID = -773441083101579225L;

  private static final Map<String, List<String>> measures = new TreeMap<>();
  private static final Map<String, Integer> mapMeasureCategory = new HashMap<>();
  private static final Map<String, Integer> mapMeasureType = new HashMap<>();

  public static final String SEPARATOR = "||||";
  static
  {
    fillMap();
  }

  private SilkPerformerListBuilder()
  {
  }

  public static List<String> getMeasurePointClasses()
  {
    return new ArrayList<>(measures.keySet());
  }

  public static List<String> getMeasureTypes(String measureCategory)
  {
    List<String> l = measures.get(measureCategory);
    return (l == null) ? new ArrayList<>() : l;
  }

  public static int getMeasureCategoryId(String measureCategory)
  {
    Integer i = mapMeasureCategory.get(measureCategory);
    return (i == null) ? -1 : i;
  }

  // key is concat of measureType + separator + measureCategory
  public static int getMeasureTypeId(String key)
  {
    Integer i = mapMeasureType.get(key);
    return (i == null) ? -1 : i;
  }

  public static String getMeasureCategoryName(int measureCategoryId)
  {
    for (Entry<String, Integer> e : mapMeasureCategory.entrySet())
    {
      if (e.getValue() == measureCategoryId)
      {
        return e.getKey();
      }
    }
    return "";
  }

  public static String getMeasureTypeName(int measureTypeId)
  {
    for (Entry<String, Integer> e : mapMeasureType.entrySet())
    {
      if (e.getValue() == measureTypeId)
      {
        return e.getKey().split(SEPARATOR)[0];
      }
    }
    return "";
  }

  private static void fillMap()
  {
    for (SilkPerformerMeasure m : SilkPerformerMeasuresList.measures)
    {
      List<String> l = measures.get(m.getMeasureCategoryName());
      if (l == null)
      {
        l = new ArrayList<>();
        measures.put(m.getMeasureCategoryName(), l);
      }
      l.add(m.getMeasureTypeName());

      if (!mapMeasureCategory.containsKey(m.getMeasureCategoryName()))
      {
        mapMeasureCategory.put(m.getMeasureCategoryName(), m.getMeasureCategoryId());
      }
      mapMeasureType.put(m.getMeasureTypeName() + SEPARATOR + m.getMeasureCategoryName(), m.getMeasureTypeId());
    }
  }

  // To generate the xml file use ...
  @SuppressWarnings("unused")
  private static void fillMapFromFile()
  {
    Document doc;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try
    {
      DocumentBuilder db = dbf.newDocumentBuilder();
      doc = db.parse("Messages.xml");
    }
    catch (Exception e)
    {
      return;
    }

    NodeList messagesListNodeList = doc.getElementsByTagName("Measures");
    if (messagesListNodeList.getLength() == 0)
    {
      return;
    }

    Element messagesListNode = (Element) messagesListNodeList.item(0);
    NodeList classPointNodeList = messagesListNode.getElementsByTagName("ClassPoint");
    for (int i = 0; i < classPointNodeList.getLength(); i++)
    {
      Element classPointNode = (Element) classPointNodeList.item(i);
      List<String> list = new ArrayList<>();
      measures.put(classPointNode.getAttribute("name"), list);
      mapMeasureCategory.put(classPointNode.getAttribute("name"), Integer.valueOf(classPointNode.getAttribute("id")));

      NodeList typeNodeList = classPointNode.getElementsByTagName("Type");
      for (int j = 0; j < typeNodeList.getLength(); j++)
      {
        Element typeNode = (Element) typeNodeList.item(j);
        list.add(typeNode.getAttribute("name"));
        mapMeasureType.put(typeNode.getAttribute("name") + SEPARATOR + classPointNode.getAttribute("name"), Integer.valueOf(typeNode.getAttribute("id")));
      }
    }
  }
}
