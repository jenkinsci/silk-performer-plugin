package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class LtcClientMessageWrapper
{
  private static Class<?> clsLtcClientMessage;
  static
  {
    try
    {
      clsLtcClientMessage = CustomClassLoader.getClazz("com.segue.em.ltc.LtcClientMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private LtcClientMessageWrapper()
  {
  }

  public static int getMessage(Object ltcClientMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcClientMessage.getClass().getMethod("getMessage");
    return (int) m.invoke(ltcClientMessage);
  }

  public static int getParam1(Object ltcClientMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcClientMessage.getClass().getMethod("getParam1");
    return (int) m.invoke(ltcClientMessage);
  }

  public static int getParam2(Object ltcClientMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcClientMessage.getClass().getMethod("getParam2");
    return (int) m.invoke(ltcClientMessage);
  }

  public static String getStrParam(Object ltcClientMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ltcClientMessage.getClass().getMethod("getStrParam");
    return (String) m.invoke(ltcClientMessage);
  }

  public static Class<?> getLtcClientMessageClass()
  {
    return clsLtcClientMessage;
  }

}
