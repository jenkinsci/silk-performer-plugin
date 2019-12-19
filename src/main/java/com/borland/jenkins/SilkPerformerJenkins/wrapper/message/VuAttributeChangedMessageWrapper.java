package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class VuAttributeChangedMessageWrapper
{
  private static Class<?> clsVuAttributeChangedMessage;
  static
  {
    try
    {
      clsVuAttributeChangedMessage = CustomClassLoader.getClazz("com.segue.em.ltc.VuAttributeChangedMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private VuAttributeChangedMessageWrapper()
  {
  }

  public static String getOldAttribute(Object vuAttributeChangedMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = vuAttributeChangedMessage.getClass().getMethod("getOldAttribute");
    return (String) m.invoke(vuAttributeChangedMessage);
  }

  public static Class<?> getVuAttributeChangedMessageClass()
  {
    return clsVuAttributeChangedMessage;
  }

}
