package com.borland.jenkins.SilkPerformerJenkins.data;

public class SPMessage
{
  int code;
  int severity;
  int time;

  public SPMessage(int code, int severity, int time)
  {
    this.code = code;
    this.severity = severity;
    this.time = time;
  }
}
