package com.borland.jenkins.SilkPerformerJenkins.wrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionInfoWrapper
{
  private TransactionInfoWrapper()
  {
  }

  public static String getTransactionName(Object ti) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
  {
    Method m = ti.getClass().getMethod("getTransactionName");
    return (String) m.invoke(ti);
  }

}
