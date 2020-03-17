package com.borland.jenkins.SilkPerformerJenkins.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class CustomClassLoader
{
  private static URLClassLoader classLoader;

  private CustomClassLoader()
  {
  }

  public static void init(String performerInstallDir, ClassLoader parent)
  {
    if (classLoader == null)
    {
      AccessController.doPrivileged(new PrivilegedAction<Void>()
      {

        @Override
        public Void run()
        {
          try
          {
            URL[] urls;
            urls = new URL[] { Paths.get(performerInstallDir, "ClassFiles\\sgem.jar").toUri().toURL() };
            classLoader = new URLClassLoader(urls, parent);
          }
          catch (MalformedURLException e)
          {
            e.printStackTrace();
          }
          return null;
        }
      });
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
