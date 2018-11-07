package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.PrintStream;

import com.segue.em.ltc.AgentMessage;
import com.segue.em.ltc.ErrorMessage;
import com.segue.em.ltc.IpcMessage;
import com.segue.em.ltc.Listener;
import com.segue.em.ltc.LtcClientMessage;
import com.segue.em.ltc.LtcInfoMessage;
import com.segue.em.ltc.LtcOutputMessage;
import com.segue.em.ltc.VuAttributeChangedMessage;
import com.segue.em.ltc.VuAttributeMessage;
import com.segue.em.ltc.VuContainerMessage;
import com.segue.em.ltc.VuMessage;
import com.segue.em.ltc.VuOutputMessage;
import com.segue.em.ltc.VuSyncMessage;

public class JenkinsSPListener implements Listener
{

  private PrintStream logger;

  public JenkinsSPListener(PrintStream logger)
  {

    this.logger = logger;
  }

  @Override
  public boolean ltcTriggered(IpcMessage evt)
  {
    if (evt instanceof AgentMessage)
    {
      AgentMessage agentMessage = (AgentMessage) evt;
      int msg = agentMessage.getMsg();
      int agent = agentMessage.getAgent();
      logger.print("Agent " + agent + " sent message "); //$NON-NLS-1$ //$NON-NLS-2$
      switch (msg)
      {
        case AgentMessage.MSG_Acknowledge:
          logger.println("Acknowledged"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Error:
          logger.println("Error"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Executed:
          logger.println("Executed"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Finished:
          logger.println("Finished"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Info:
          logger.println("Info"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Ping:
          logger.println("Ping"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Result:
          logger.println("Result"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Started:
          logger.println("Started"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Status:
          logger.println("Status"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_StatusProgress:
          logger.println("Status Progress"); //$NON-NLS-1$
          break;
        case AgentMessage.MSG_Warning:
          logger.println("Warning"); //$NON-NLS-1$
          break;
        default:
          logger.println(msg);
      }
    }
    else if (evt instanceof ErrorMessage)
    {
      ErrorMessage msg = (ErrorMessage) evt;
      int errCode = msg.getErrCode();
      logger.println("Error message received " + errCode); //$NON-NLS-1$
    }
    else if (evt instanceof LtcClientMessage)
    {
      LtcClientMessage msg = (LtcClientMessage) evt;
      int msgId = msg.getMessage();
      int par1 = msg.getParam1();
      int par2 = msg.getParam2();
      String strPar = msg.getStrParam();
    }
    else if (evt instanceof LtcInfoMessage)
    {
      LtcInfoMessage msg = (LtcInfoMessage) evt;
      String compName = msg.getComputerName();
      int tlocal = msg.getTimeLocal();
      int tutc = msg.getTimeUtc();
      logger.println("LtcInfoMessage: comp=" + compName + ", times = " + tlocal + "," + tutc); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    else if (evt instanceof LtcOutputMessage)
    {
      LtcOutputMessage msg = (LtcOutputMessage) evt;
      int ipar = msg.getIntParam();
      String spar = msg.getStringParam();
    }
    else if (evt instanceof VuAttributeMessage)
    {
      VuAttributeMessage msg = (VuAttributeMessage) evt;
      int agent = msg.getAgent();
      int acmd = msg.getAttCommand();
      String attName = msg.getAttName();
      String attValue = msg.getAttValue();
      int vu = msg.getVu();
      String oldValue = null;
      if (msg instanceof VuAttributeChangedMessage)
      {
        VuAttributeChangedMessage msg2 = (VuAttributeChangedMessage) msg;
        oldValue = msg2.getOldAttribute();
        logger.print("VuAttributeChanged: "); //$NON-NLS-1$
      }
      else
      {
        logger.print("VuAttribute: "); //$NON-NLS-1$
      }
      logger.print("agent = " + agent + ", vu = " + vu + " cmd = " + acmd + " name = " + attName + " value = " + attValue); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
      if (oldValue != null)
      {
        logger.println(" oldVal = " + oldValue); //$NON-NLS-1$
      }
      else
      {
        logger.println();
      }
    }
    else if (evt instanceof VuContainerMessage)
    {
      VuContainerMessage msg = (VuContainerMessage) evt;
      int agent = msg.getAgent();
      int err = msg.getError();
      int firstVu = msg.getFirstVu();
      int lastVu = msg.getLastVu();
      int msgId = msg.getMsg();
      int vucId = msg.getVuContainer();
      logger.print("VuContainer " + vucId + " on " + agent + "(" + firstVu + "-" + lastVu + ") "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
      switch (msgId)
      {
        case VuContainerMessage.MSG_Error:
          logger.println("reported err " + err); //$NON-NLS-1$
          break;
        case VuContainerMessage.MSG_Started:
          logger.println("started"); //$NON-NLS-1$
          break;
        case VuContainerMessage.MSG_Finished:
          logger.println("finished"); //$NON-NLS-1$
          break;
        default:
          logger.println("reporting unknown message " + msgId); //$NON-NLS-1$
      }
    }
    else if (evt instanceof VuMessage)
    {
      VuMessage msg = (VuMessage) evt;
      int agent = msg.getAgent();
      int msgId = msg.getMsg();
      int vu = msg.getVu();
      logger.print("VuMessage from " + vu + " on " + agent + " reported "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      switch (msgId)
      {
        case VuMessage.MSG_Error:
          logger.println("error"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Finished:
          logger.println("finished"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_FirstResult:
          logger.println("first result"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Info:
          logger.println("info"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_LastResult:
          logger.println("last result"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Result:
          logger.println("result"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Scheduled:
          logger.println("scheduled"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Started:
          logger.println("started"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Status:
          logger.println("status"); //$NON-NLS-1$
          break;
        case VuMessage.MSG_Warning:
          logger.println("warning"); //$NON-NLS-1$
          break;
        default:
          logger.println("unknown message " + msgId); //$NON-NLS-1$
      }
    }
    else if (evt instanceof VuOutputMessage)
    {
      VuOutputMessage msg = (VuOutputMessage) evt;
      int agent = msg.getAgent();
      int color = msg.getColor();
      String descLong = msg.getDescriptionLong();
      String descShort = msg.getDescriptionShort();
      int line = msg.getLineNo();
      int type = msg.getOutputType();
      int spix = msg.getScriptSpix();
      int ts = msg.getTimestamp();
      int vu = msg.getVu();
      logger.println("Vu " + vu + " on " + agent + " in line " + line + "(" + spix + ") reported at " + ts); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
      switch (type)
      {
        case VuOutputMessage.VU_OUTPUT_Custom:
          logger.println("custom message " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Data:
          logger.println("data " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Error:
          logger.println("error " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Function:
          logger.println("function " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Info:
          logger.println("info message " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_ProcExit:
          logger.println("procedure exit " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Timer:
          logger.println("timer " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Transaction:
          logger.println("transaction " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_TransExit:
          logger.println("transaction exit " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Warning:
          logger.println("warning " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Checkpoint_SUCCESS:
          logger.println("checkpoint(success) " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Checkpoint_WARNING:
          logger.println("checkpoint(warning) " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        case VuOutputMessage.VU_OUTPUT_Checkpoint_ERROR:
          logger.println("checkpoint(error) " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
          break;
        default:
          logger.println("unknown message " + descShort + ", " + descLong); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }
    else if (evt instanceof VuSyncMessage)
    {
      VuSyncMessage msg = (VuSyncMessage) evt;
      int agent = msg.getAgent();
      int scmd = msg.getSyncCommand();
      int sid = msg.getSyncId();
      String sname = msg.getSyncName();
      int svalue = msg.getSyncValue();
      int vu = msg.getVu();
      logger.println("vu " + vu + " on " + agent + " synchronized " + sname + "," + svalue + "," + scmd + "," + sid); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
    }
    else
    {
      logger.println("Message not handled - no appropriate event found " + evt.getClass());
    }
    return true;
  }

}
