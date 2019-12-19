package com.borland.jenkins.SilkPerformerJenkins;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import com.borland.jenkins.SilkPerformerJenkins.wrapper.SGExecutionManagerWrapper;

import jenkins.security.MasterToSlaveCallable;

public class CleanupNode extends MasterToSlaveCallable<Boolean, IOException> implements Serializable
{
  private static final long serialVersionUID = -4487600224362892778L;

  @Override
  public Boolean call() throws IOException
  {
    try
    {
      SGExecutionManagerWrapper.abortAllLoadtests();
    }
    catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
    {
      e.printStackTrace();
    }
    return true;
  }

}
