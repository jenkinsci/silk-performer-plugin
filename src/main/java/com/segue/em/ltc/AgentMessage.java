package com.segue.em.ltc;

public class AgentMessage extends IpcMessage
{
  public static final int MSG_Acknowledge = 1;
  public static final int MSG_Ping = 2;
  public static final int MSG_Started = 3;
  public static final int MSG_Status = 4;
  public static final int MSG_StatusProgress = 5;
  public static final int MSG_Executed = 6;
  public static final int MSG_Result = 7;
  public static final int MSG_Info = 96;
  public static final int MSG_Warning = 97;
  public static final int MSG_Error = 98;
  public static final int MSG_Finished = 99;

  public int getAgent()
  {
    return 0;
  }

  public int getMsg()
  {
    return 0;
  }
}
