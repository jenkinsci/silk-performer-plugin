package com.borland.jenkins.SilkPerformerJenkins.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomClassLoader
{
  private static URLClassLoader classLoader;

  private CustomClassLoader()
  {
  }

  public static void init(String performerInstallDir, ClassLoader parent) throws MalformedURLException
  {
    if (classLoader == null)
    {
      Path p = Paths.get(performerInstallDir, "ClassFiles\\sgem.jar");
      URL[] urls = new URL[] { p.toUri().toURL() };
      classLoader = new URLClassLoader(urls, parent);
    }
  }

  public static Class<?> getClazz(String name) throws ClassNotFoundException
  {
    return classLoader.loadClass(name);
  }

  public static ClassLoader getCustomClassLoader()
  {
    return classLoader;
  }
}
