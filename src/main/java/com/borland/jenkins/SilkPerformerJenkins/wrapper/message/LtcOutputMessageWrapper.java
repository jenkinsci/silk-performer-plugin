package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class LtcOutputMessageWrapper
{
  private static Class<?> clsLtcOutputMessage;
  static
  {
    try
    {
      clsLtcOutputMessage = CustomClassLoader.getClazz("com.segue.em.ltc.LtcOutputMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private LtcOutputMessageWrapper()
  {
  }

  public static int getIntParam(Object ltcOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcOutputMessage.getClass().getMethod("getIntParam");
    return (int) m.invoke(ltcOutputMessage);
  }

  public static String getStringParam(Object ltcOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcOutputMessage.getClass().getMethod("getStringParam");
    return (String) m.invoke(ltcOutputMessage);
  }

  public static Class<?> getLtcOutputMessageClass()
  {
    return clsLtcOutputMessage;
  }

}
