package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class VuContainerMessageWrapper
{
  private static Class<?> clsVuContainerMessage;
  static
  {
    try
    {
      clsVuContainerMessage = CustomClassLoader.getClazz("com.segue.em.ltc.VuContainerMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private VuContainerMessageWrapper()
  {
  }

  public static int getVuContainer(Object vuContainerMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuContainerMessage.getClass().getMethod("getVuContainer");
    return (int) m.invoke(vuContainerMessage);
  }

  public static int getAgent(Object vuContainerMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuContainerMessage.getClass().getMethod("getAgent");
    return (int) m.invoke(vuContainerMessage);
  }

  public static int getMsg(Object vuContainerMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuContainerMessage.getClass().getMethod("getMsg");
    return (int) m.invoke(vuContainerMessage);
  }

  public static int getError(Object vuContainerMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuContainerMessage.getClass().getMethod("getError");
    return (int) m.invoke(vuContainerMessage);
  }

  public static int getFirstVu(Object vuContainerMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuContainerMessage.getClass().getMethod("getFirstVu");
    return (int) m.invoke(vuContainerMessage);
  }

  public static int getLastVu(Object vuContainerMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuContainerMessage.getClass().getMethod("getLastVu");
    return (int) m.invoke(vuContainerMessage);
  }

  public static int getAttribute(String name) throws NoSuchFieldException, IllegalAccessException
  {
    Field f = clsVuContainerMessage.getField(name);
    return f.getInt(null);
  }

  public static Class<?> getVuContainerMessageClass()
  {
    return clsVuContainerMessage;
  }

}
