package com.borland.jenkins.SilkPerformerJenkins;

import java.io.IOException;
import java.io.Serializable;

import com.segue.em.SGExecutionManager;

import jenkins.security.MasterToSlaveCallable;

public class CleanupNode extends MasterToSlaveCallable<Boolean, IOException> implements Serializable
{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  @Override
  public Boolean call() throws IOException
  {
    SGExecutionManager.abortAllLoadtests();
    return null;
  }

}
