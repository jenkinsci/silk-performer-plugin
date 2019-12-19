package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class VuSyncMessageWrapper
{
  private static Class<?> clsVuSyncMessage;
  static
  {
    try
    {
      clsVuSyncMessage = CustomClassLoader.getClazz("com.segue.em.ltc.VuSyncMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private VuSyncMessageWrapper()
  {
  }

  public static int getVu(Object vuSyncMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuSyncMessage.getClass().getMethod("getVu");
    return (int) m.invoke(vuSyncMessage);
  }

  public static int getAgent(Object vuSyncMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuSyncMessage.getClass().getMethod("getAgent");
    return (int) m.invoke(vuSyncMessage);
  }

  public static int getSyncId(Object vuSyncMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuSyncMessage.getClass().getMethod("getSyncId");
    return (int) m.invoke(vuSyncMessage);
  }

  public static int getSyncCommand(Object vuSyncMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuSyncMessage.getClass().getMethod("getSyncCommand");
    return (int) m.invoke(vuSyncMessage);
  }

  public static String getSyncName(Object vuSyncMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuSyncMessage.getClass().getMethod("getSyncName");
    return (String) m.invoke(vuSyncMessage);
  }

  public static int getSyncValue(Object vuSyncMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuSyncMessage.getClass().getMethod("getSyncValue");
    return (int) m.invoke(vuSyncMessage);
  }

  public static Class<?> getVuSyncMessageClass()
  {
    return clsVuSyncMessage;
  }

}
