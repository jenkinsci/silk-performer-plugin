package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.Field;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class ISGExecutionManagerWrapper
{
  private static Class<?> clsISGExecutionManager;
  static
  {
    try
    {
      clsISGExecutionManager = CustomClassLoader.getClazz("com.segue.em.SGExecutionManager");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private ISGExecutionManagerWrapper()
  {
  }

  public static int getAttribute(String name) throws NoSuchFieldException, IllegalAccessException
  {
    Field f = clsISGExecutionManager.getField(name);
    return f.getInt(null);
  }
}
