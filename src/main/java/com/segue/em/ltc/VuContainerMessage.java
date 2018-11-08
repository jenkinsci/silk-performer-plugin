package com.segue.em.ltc;

public class VuContainerMessage extends IpcMessage
{
  public static final int MSG_Started = 1;
  public static final int MSG_Finished = 2;
  public static final int MSG_Error = 3;
  
  public int getVuContainer()
  {
    return 0;
  }

  public int getAgent()
  {
    return 0;
  }

  public int getMsg()
  {
    return 0;
  }

  public int getError()
  {
    return 0;
  }

  public int getFirstVu()
  {
    return 0;
  }

  public int getLastVu()
  {
    return 0;
  }
}
