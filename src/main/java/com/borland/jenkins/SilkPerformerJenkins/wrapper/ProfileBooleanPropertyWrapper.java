package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class ProfileBooleanPropertyWrapper
{
  private static Class<?> clsProfileBooleanProperty;
  static
  {
    try
    {
      clsProfileBooleanProperty = CustomClassLoader.getClazz("com.segue.em.projectfile.ProfileBooleanProperty");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private ProfileBooleanPropertyWrapper()
  {
  }

  public static Object getInstance(Object prjf, String propertyName, boolean value)
      throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
  {
    Constructor<?> constr = clsProfileBooleanProperty.getConstructor(prjf.getClass(), String.class, Boolean.TYPE);
    return constr.newInstance(prjf, propertyName, Boolean.valueOf(value));
  }

  public static Class<?> getProfileBooleanPropertyClass()
  {
    return clsProfileBooleanProperty;
  }

}
