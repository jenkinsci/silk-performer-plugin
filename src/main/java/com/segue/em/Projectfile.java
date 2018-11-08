package com.segue.em;

import java.util.Iterator;

import com.segue.em.projectfile.ProfileBooleanProperty;
import com.segue.em.projectfile.TransactionInfo;
import com.segue.em.projectfile.UserType;

public class Projectfile extends SgemCapableObject implements IProjectfile
{
  public synchronized void close()
  {
  }

  public void save()
  {
  }
  
  public String getActiveWorkload()
  {
    return "";
  }

  public void setCurrentWorkload(String csWLName)
  {
  }

  public UserType getFirstUserType()
  {
    return null;
  }

  public UserType getNextUserType()
  {
    return null;
  }

  public synchronized Iterator<TransactionInfo> getTransactionInfo()
  {
    return null;
  }

  public void setProfileBooleanProperty(ProfileBooleanProperty prop)
  {
  }
  
  public void setResultsDir(String newDir)
  {
  }
  
  public void setActiveWorkload(String csWLName)
  {
  }
}
