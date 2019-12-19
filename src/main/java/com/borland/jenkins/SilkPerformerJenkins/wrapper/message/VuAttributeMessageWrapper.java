package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class VuAttributeMessageWrapper
{
  private static Class<?> clsVuAttributeMessage;
  static
  {
    try
    {
      clsVuAttributeMessage = CustomClassLoader.getClazz("com.segue.em.ltc.VuAttributeMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private VuAttributeMessageWrapper()
  {
  }

  public static int getVu(Object vuAttributeMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuAttributeMessage.getClass().getMethod("getVu");
    return (int) m.invoke(vuAttributeMessage);
  }

  public static int getAgent(Object vuAttributeMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuAttributeMessage.getClass().getMethod("getAgent");
    return (int) m.invoke(vuAttributeMessage);
  }

  public static int getAttCommand(Object vuAttributeMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuAttributeMessage.getClass().getMethod("getAttCommand");
    return (int) m.invoke(vuAttributeMessage);
  }

  public static String getAttName(Object vuAttributeMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuAttributeMessage.getClass().getMethod("getAttName");
    return (String) m.invoke(vuAttributeMessage);
  }

  public static String getAttValue(Object vuAttributeMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuAttributeMessage.getClass().getMethod("getAttValue");
    return (String) m.invoke(vuAttributeMessage);
  }

  public static Class<?> getVuAttributeMessageClass()
  {
    return clsVuAttributeMessage;
  }

}
