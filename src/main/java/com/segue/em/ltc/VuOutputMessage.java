package com.segue.em.ltc;

public class VuOutputMessage extends IpcMessage
{
  public static final int VU_OUTPUT_Info = 0;
  public static final int VU_OUTPUT_Warning = 1;
  public static final int VU_OUTPUT_Error = 2;
  public static final int VU_OUTPUT_TransExit = 3;
  public static final int VU_OUTPUT_ProcExit = 4;
  public static final int VU_OUTPUT_Transaction = 5;
  public static final int VU_OUTPUT_Timer = 6;
  public static final int VU_OUTPUT_Function = 7;
  public static final int VU_OUTPUT_Data = 8;
  public static final int VU_OUTPUT_Custom = 9;
  public static final int VU_OUTPUT_Checkpoint_SUCCESS = 10;
  public static final int VU_OUTPUT_Checkpoint_WARNING = 11;
  public static final int VU_OUTPUT_Checkpoint_ERROR = 12;
  
  public int getVu()
  {
    return 0;
  }

  public int getAgent()
  {
    return 0;
  }

  public int getOutputType()
  {
    return 0;
  }

  public int getLineNo()
  {
    return 0;
  }

  public int getScriptSpix()
  {
    return 0;
  }

  public int getTimestamp()
  {
    return 0;
  }

  public String getDescriptionShort()
  {
    return "";
  }

  public String getDescriptionLong()
  {
    return "";
  }

  public int getColor()
  {
    return 0;
  }
}
