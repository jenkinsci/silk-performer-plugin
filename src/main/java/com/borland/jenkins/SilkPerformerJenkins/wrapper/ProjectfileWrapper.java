package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ProjectfileWrapper
{
  private ProjectfileWrapper()
  {
  }

  public static void setProfileBooleanProperty(Object prjf, String propName, boolean value)
      throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("setProfileBooleanProperty", ProfileBooleanPropertyWrapper.getProfileBooleanPropertyClass());
    Object prflBooleanProperty = ProfileBooleanPropertyWrapper.getInstance(prjf, propName, value);
    m.invoke(prjf, prflBooleanProperty);
  }

  public static String getActiveWorkload(Object prjf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("getActiveWorkload");
    return (String) m.invoke(prjf);
  }

  public static void setCurrentWorkload(Object prjf, String csWLName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("setCurrentWorkload", String.class);
    m.invoke(prjf, csWLName);
  }

  public static void setResultsDir(Object prjf, String newDir) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("setResultsDir", String.class);
    m.invoke(prjf, newDir);
  }

  public static void setActiveWorkload(Object prjf, String csWLName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("setActiveWorkload", String.class);
    m.invoke(prjf, csWLName);
  }

  public static void save(Object prjf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("save");
    m.invoke(prjf);
  }

  public static void close(Object prjf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("close");
    m.invoke(prjf);
  }

  public static Object getFirstUserType(Object prjf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("getFirstUserType");
    return m.invoke(prjf);
  }

  public static Object getNextUserType(Object prjf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("getNextUserType");
    return m.invoke(prjf);
  }

  public static Iterator<Object> getTransactionInfo(Object prjf) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = prjf.getClass().getMethod("getTransactionInfo");
    return (Iterator<Object>) m.invoke(prjf);
  }

}
