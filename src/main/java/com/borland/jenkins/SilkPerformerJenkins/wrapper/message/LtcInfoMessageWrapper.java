package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class LtcInfoMessageWrapper
{
  private static Class<?> clsLtcInfoMessage;
  static
  {
    try
    {
      clsLtcInfoMessage = CustomClassLoader.getClazz("com.segue.em.ltc.LtcInfoMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private LtcInfoMessageWrapper()
  {
  }

  public static String getComputerName(Object ltcInfoMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcInfoMessage.getClass().getMethod("getComputerName");
    return (String) m.invoke(ltcInfoMessage);
  }

  public static int getTimeLocal(Object ltcInfoMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcInfoMessage.getClass().getMethod("getTimeLocal");
    return (int) m.invoke(ltcInfoMessage);
  }

  public static int getTimeUtc(Object ltcInfoMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcInfoMessage.getClass().getMethod("getTimeUtc");
    return (int) m.invoke(ltcInfoMessage);
  }

  public static Class<?> getLtcInfoMessageClass()
  {
    return clsLtcInfoMessage;
  }

}
