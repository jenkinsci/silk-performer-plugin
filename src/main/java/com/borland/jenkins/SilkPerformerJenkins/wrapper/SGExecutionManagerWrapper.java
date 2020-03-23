package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class SGExecutionManagerWrapper
{
  private static Class<?> clsSgExecManager;
  static
  {
    try
    {
      clsSgExecManager = CustomClassLoader.getClazz("com.segue.em.SGExecutionManager");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private SGExecutionManagerWrapper()
  {
  }

  public static int getAttribute(String name) throws NoSuchFieldException, IllegalAccessException
  {
    Field f = clsSgExecManager.getField(name);
    return f.getInt(null);
  }

  public static void abortAllLoadtests() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("abortAllLoadtests");
    m.invoke(null);
  }

  public static Object openProject(String sFilename) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("openProject", String.class);
    return m.invoke(null, sFilename);
  }

  public static void setMessagingMode(int mode) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("setMessagingMode", int.class);
    m.invoke(null, mode);
  }

  public static Object createController(String sConfigFilename) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("createController", String.class);
    return m.invoke(null, sConfigFilename);
  }

  public static void destroyController(Object ltc) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
  {
    Method m = clsSgExecManager.getMethod("destroyController", LtcWrapper.getLtcClass());
    m.invoke(null, ltc);
  }

  public static int getMeasureClass(int eMeasureType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("getMeasureClass", int.class);
    return (int) m.invoke(null, eMeasureType);
  }

  public static String getMeasureClassName(int eMeasureClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("getMeasureClassName", int.class);
    return (String) m.invoke(null, eMeasureClass);
  }

  public static String getMeasureTypeName(int eMeasureType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = clsSgExecManager.getMethod("getMeasureTypeName");
    return (String) m.invoke(null, eMeasureType);
  }

  public static Class<?> getSgExecutionManagerClass()
  {
    return clsSgExecManager;
  }
}
