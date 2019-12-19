package com.borland.jenkins.SilkPerformerJenkins.wrapper.message;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.borland.jenkins.SilkPerformerJenkins.util.CustomClassLoader;

public class AgentMessageWrapper
{
  private static Class<?> clsAgentMessage;
  static
  {
    try
    {
      clsAgentMessage = CustomClassLoader.getClazz("com.segue.em.ltc.AgentMessage");
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  private AgentMessageWrapper()
  {
  }

  public static int getAgent(Object agentMessage) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
  {
    Method m = agentMessage.getClass().getMethod("getAgent");
    return (int) m.invoke(agentMessage);
  }

  public static int getMsg(Object agentMessage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
  {
    Method m = agentMessage.getClass().getMethod("getMsg");
    return (int) m.invoke(agentMessage);
  }

  public static int getAttribute(String name) throws NoSuchFieldException, IllegalAccessException
  {
    Field f = clsAgentMessage.getField(name);
    return f.getInt(null);
  }

  public static Class<?> getAgentMessageClass()
  {
    return clsAgentMessage;
  }
}
