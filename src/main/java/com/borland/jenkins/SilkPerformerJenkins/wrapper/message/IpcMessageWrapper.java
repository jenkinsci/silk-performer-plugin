package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class IpcMessageWrapper
{
  private static Class<?> clsIpcMessage;
  static
  {
    try
    {
      clsIpcMessage = CustomClassLoader.getClazz("com.segue.em.ltc.IpcMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private IpcMessageWrapper()
  {
  }

  public static Class<?> getIpcMessageClass()
  {
    return clsIpcMessage;
  }
}
