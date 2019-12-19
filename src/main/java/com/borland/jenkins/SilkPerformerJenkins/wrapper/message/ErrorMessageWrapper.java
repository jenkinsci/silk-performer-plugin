package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class ErrorMessageWrapper
{
  private static Class<?> clsErrorMessage;
  static
  {
    try
    {
      clsErrorMessage = CustomClassLoader.getClazz("com.segue.em.ltc.ErrorMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private ErrorMessageWrapper()
  {
  }

  public static int getErrCode(Object errorMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = errorMessage.getClass().getMethod("getErrCode");
    return (int) m.invoke(errorMessage);
  }

  public static Class<?> getErrorMessageClass()
  {
    return clsErrorMessage;
  }
}
