package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserTypeWrapper
{
  private UserTypeWrapper()
  {
  }

  public static String getScriptName(Object ut) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ut.getClass().getMethod("getScriptName");
    return (String) m.invoke(ut);
  }

  public static String getUsergroupName(Object ut) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ut.getClass().getMethod("getUsergroupName");
    return (String) m.invoke(ut);
  }

  public static String getProfileName(Object ut) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = ut.getClass().getMethod("getProfileName");
    return (String) m.invoke(ut);
  }
}
