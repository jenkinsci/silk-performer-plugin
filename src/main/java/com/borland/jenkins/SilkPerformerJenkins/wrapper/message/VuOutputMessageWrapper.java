package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class VuOutputMessageWrapper
{
  private static Class<?> clsVuOutputMessage;
  static
  {
    try
    {
      clsVuOutputMessage = CustomClassLoader.getClazz("com.segue.em.ltc.VuOutputMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private VuOutputMessageWrapper()
  {
  }

  public static int getVu(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getVu");
    return (int) m.invoke(vuOutputMessage);
  }

  public static int getAgent(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getAgent");
    return (int) m.invoke(vuOutputMessage);
  }

  public static int getOutputType(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getOutputType");
    return (int) m.invoke(vuOutputMessage);
  }

  public static int getLineNo(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getLineNo");
    return (int) m.invoke(vuOutputMessage);
  }

  public static int getScriptSpix(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getScriptSpix");
    return (int) m.invoke(vuOutputMessage);
  }

  public static int getTimestamp(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getTimestamp");
    return (int) m.invoke(vuOutputMessage);
  }

  public static String getDescriptionShort(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getDescriptionShort");
    return (String) m.invoke(vuOutputMessage);
  }

  public static String getDescriptionLong(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getDescriptionLong");
    return (String) m.invoke(vuOutputMessage);
  }

  public static int getColor(Object vuOutputMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuOutputMessage.getClass().getMethod("getColor");
    return (int) m.invoke(vuOutputMessage);
  }

  public static int getAttribute(String name) throws NoSuchFieldException, IllegalAccessException
  {
    Field f = clsVuOutputMessage.getField(name);
    return f.getInt(null);
  }

  public static Class<?> getVuOutputMessageClass()
  {
    return clsVuOutputMessage;
  }

}
