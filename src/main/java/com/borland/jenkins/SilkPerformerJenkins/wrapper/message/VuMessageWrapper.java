package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class VuMessageWrapper
{
  private static Class<?> clsVuMessage;
  static
  {
    try
    {
      clsVuMessage = CustomClassLoader.getClazz("com.segue.em.ltc.VuMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private VuMessageWrapper()
  {
  }

  public static int getVu(Object vuMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuMessage.getClass().getMethod("getVu");
    return (int) m.invoke(vuMessage);
  }

  public static int getAgent(Object vuMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuMessage.getClass().getMethod("getAgent");
    return (int) m.invoke(vuMessage);
  }

  public static int getMsg(Object vuMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuMessage.getClass().getMethod("getMsg");
    return (int) m.invoke(vuMessage);
  }

  public static int getAttribute(String name) throws NoSuchFieldException, IllegalAccessException
  {
    Field f = clsVuMessage.getField(name);
    return f.getInt(null);
  }

  public static Class<?> getVuMessageClass()
  {
    return clsVuMessage;
  }

}
