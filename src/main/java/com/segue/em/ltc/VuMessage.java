package com.segue.em.ltc;

public class VuMessage extends IpcMessage
{
  public static final int MSG_Scheduled = 1;
  public static final int MSG_Started = 2;
  public static final int MSG_Status = 3;
  public static final int MSG_FirstResult = 4;
  public static final int MSG_Result = 5;
  public static final int MSG_LastResult = 6;
  public static final int MSG_Info = 96;
  public static final int MSG_Warning = 97;
  public static final int MSG_Error = 98;
  public static final int MSG_Finished = 99;
  
  public int getVu()
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
}
