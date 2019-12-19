package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class ListenerWrapper
{
  private static Class<?> clsListener;
  static
  {
    try
    {
      clsListener = CustomClassLoader.getClazz("com.segue.em.ltc.Listener");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private ListenerWrapper()
  {
  }

  public static Class<?> getListenerClass()
  {
    return clsListener;
  }
}
