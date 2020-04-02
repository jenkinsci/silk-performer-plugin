package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class LtcWrapper
{
  private static Class<?> clsLtc;
  static
  {
    try
    {
      clsLtc = CustomClassLoader.getClazz("com.segue.em.Ltc");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private LtcWrapper()
  {
  }

  public static void addEventListener(Object ltc, Object listener) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
  {
    Method m = ltc.getClass().getMethod("addEventListener", ListenerWrapper.getListenerClass());
    m.invoke(ltc, listener);
  }

  public static void setDeployBuildOption(Object ltc, int delployOption) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("setDeployBuildOption", int.class);
    m.invoke(ltc, delployOption);
  }

  public static void deployProject(Object ltc, String prjFileName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("deployProject", String.class);
    m.invoke(ltc, prjFileName);
  }

  public static void deployProjectEx(Object ltc, String prjFileName, int iFlags) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("deployProjectEx", String.class, int.class);
    m.invoke(ltc, prjFileName, iFlags);
  }

  public static void setVuOutputOptions(Object ltc, int uOptions) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("setVuOutputOptions", int.class);
    m.invoke(ltc, uOptions);
  }

  public static void start(Object ltc, int iTimeout) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("start", int.class);
    m.invoke(ltc, iTimeout);
  }

  public static void undeploy(Object ltc) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("undeploy");
    m.invoke(ltc);
  }

  public static void destroy(Object ltc) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltc.getClass().getMethod("destroy");
    m.invoke(ltc);
  }

  public static Class<?> getLtcClass()
  {
    return clsLtc;
  }
}
