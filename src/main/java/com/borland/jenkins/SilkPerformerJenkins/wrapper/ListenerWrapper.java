package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.IpcMessageWrapper;

public class ListenerWrapper
{
  private static Class<?> clsListener;
  static
  {
    try
    {
      clsListener = CustomClassLoader.getClazz("com.segue.em.ltc.Listener");
      // Just to be sure that IpcMessage class is loaded
      IpcMessageWrapper.getIpcMessageClass();
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
