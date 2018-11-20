package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import hudson.model.BuildListener;

public class SystemUtils
{
  public static void initSystem(String performerInstallDir, BuildListener listener)
  {
    loadSgemJar(performerInstallDir, listener);
    addToJavaLib(performerInstallDir, listener);
  }
  
  public static void loadSgemJar(String performerInstallDir, BuildListener listener) {
    try
    {
      File ff = new File(performerInstallDir + "\\ClassFiles\\sgem.jar");
      URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
      Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
      method.setAccessible(true);
      method.invoke(sysloader, new Object[] { ff.toURI().toURL() });

      listener.getLogger().println("Object = " + ff.exists());
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | MalformedURLException e)
    {
      listener.getLogger().println("Cannot load sgem.jar.");
      e.printStackTrace(listener.getLogger());
    }
  }

  public static void addToJavaLib(String performerInstallDir, BuildListener listener)
  {
    try
    {
      System.setProperty("java.library.path", performerInstallDir);
      Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
      fieldSysPath.setAccessible(true);
      fieldSysPath.set(null, null);
    }
    catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
    {
      listener.getLogger().println("Cannot extend java library path.");
      e.printStackTrace(listener.getLogger());
    }
  }
}
