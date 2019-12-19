package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import hudson.model.BuildListener;

public class SystemUtils
{
  private static boolean isSystemInitialized = false;

  private SystemUtils()
  {
  }

  public static void initSystem(String performerInstallDir, BuildListener listener)
  {
    if (!isSystemInitialized)
    {
      addToSystemJavaLibPath(performerInstallDir, listener);
      addToUserJavaLibPath(performerInstallDir, listener);
      String nativeLibsPath = getNativeLibrariesPath(performerInstallDir);
      if (!performerInstallDir.equalsIgnoreCase(nativeLibsPath))
      {
        addToSystemJavaLibPath(nativeLibsPath, listener);
        addToUserJavaLibPath(nativeLibsPath, listener);
      }
      loadNativeLibraries(nativeLibsPath, listener);
      // loadSgemJar(performerInstallDir, listener);

      isSystemInitialized = true;
    }
  }

  private static void loadSgemJar(String performerInstallDir, BuildListener listener)
  {
    try
    {
      ClassLoader clsLoader = ClassLoader.getSystemClassLoader();
      if (URLClassLoader.class.isInstance(clsLoader))
      {
        File ff = new File(performerInstallDir + "\\ClassFiles\\sgem.jar");
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(ClassLoader.getSystemClassLoader(), ff.toURI().toURL());

        listener.getLogger().println(ff.getAbsolutePath() + " is loaded!");
      }
      else
      {
        listener.getLogger().println("No URLClassLoader! " + clsLoader.getClass());
      }
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | MalformedURLException e)
    {
      listener.error("Cannot load sgem.jar.");
      e.printStackTrace(listener.getLogger());
    }
  }

  private static void addToSystemJavaLibPath(String performerInstallDir, BuildListener listener)
  {
    try
    {
      String s = System.getProperty("java.library.path");
      if (!s.contains(performerInstallDir))
      {
        listener.getLogger().println(performerInstallDir + " is added to java.library.path");
        s = performerInstallDir + ";" + s;
        System.setProperty("java.library.path", s);
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);
      }
    }
    catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
    {
      listener.error("Cannot extend java library path.");
      e.printStackTrace(listener.getLogger());
    }
  }

  private static void addToUserJavaLibPath(String nativeLibPath, BuildListener listener)
  {
    try
    {
      // This enables the java.library.path to be modified at runtime
      // From a Sun engineer at http://forums.sun.com/thread.jspa?threadID=707176
      //
      Field field = ClassLoader.class.getDeclaredField("usr_paths");
      field.setAccessible(true);
      String[] paths = (String[]) field.get(null);
      for (int i = 0; i < paths.length; i++)
      {
        if (nativeLibPath.equals(paths[i]))
        {
          return;
        }
      }
      String[] tmp = new String[paths.length + 1];
      System.arraycopy(paths, 0, tmp, 0, paths.length);
      tmp[paths.length] = nativeLibPath;
      field.set(null, tmp);
      System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + nativeLibPath);
    }
    catch (IllegalAccessException | NoSuchFieldException e)
    {
      listener.error("Cannot extend java library path.");
      e.printStackTrace(listener.getLogger());
    }
  }

  private static void loadNativeLibraries(String nativeLibsPath, BuildListener listener)
  {
    try
    {
      System.load(nativeLibsPath + "\\locStringsCommon.dll");
      System.load(nativeLibsPath + "\\locStringsKernelCommon.dll");
      System.load(nativeLibsPath + "\\locStringsKernelFeatures.dll");
      System.load(nativeLibsPath + "\\perfVersion.dll");
      System.load(nativeLibsPath + "\\perfBexScanner.dll");
      System.load(nativeLibsPath + "\\perfXmlReports.dll");
      System.load(nativeLibsPath + "\\perfTsd.dll");
      System.load(nativeLibsPath + "\\perfBdlScanner.dll");
      System.load(nativeLibsPath + "\\sgExecManager.dll");
      System.load(nativeLibsPath + "\\perfMessages.dll");
      System.load(nativeLibsPath + "\\perfLm.dll");
      // We need to load these libraries to be able to load sgemBridge.dll
    }
    catch (Exception e)
    {
      listener.error("Cannot load SP specific native libraries.");
      e.printStackTrace(listener.getLogger());
    }
  }

  private static String getNativeLibrariesPath(String performerInstallDir)
  {
    String spInstallDir = performerInstallDir.toLowerCase();
    String archDataModel = System.getProperty("sun.arch.data.model");
    if (archDataModel.equals("64"))
    {
      Path p = Paths.get(spInstallDir, "X64");
      if (p.toFile().exists())
      {
        return p.toString();
      }

      String programFiles86 = System.getenv("ProgramFiles(X86)").toLowerCase();
      if (spInstallDir.contains(programFiles86))
      {
        String programFiles = System.getenv("ProgramFiles").toLowerCase();
        String s = spInstallDir.replace(programFiles86, programFiles);
        p = Paths.get(s);
        if (p.toFile().exists())
        {
          return p.toString();
        }
      }
    }
    return performerInstallDir;
  }
}
