package com.borland.jenkins.SilkPerformerJenkins.util;

import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.borland.jenkins.SilkPerformerJenkins.wrapper.ListenerWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.AgentMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.ErrorMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.IpcMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.LtcClientMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.LtcInfoMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.LtcOutputMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.VuAttributeChangedMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.VuAttributeMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.VuContainerMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.VuMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.VuOutputMessageWrapper;
import com.borland.jenkins.SilkPerformerJenkins.wrapper.message.VuSyncMessageWrapper;

public class JenkinsSPListener
{
  private JenkinsSPListener()
  {
  }

  public static Object getListener(PrintStream logger) throws ClassNotFoundException
  {
    // if (listener == null)
    // {
    // listener = initListener(logger);
    // }
    // return listener;
    return initListener(logger);
  }

  public static Object initListener(PrintStream logger) throws ClassNotFoundException
  {
    logger.println("------------initListener----------------");
    Class<?> ipcMessageCls = IpcMessageWrapper.getIpcMessageClass();
    logger.println("------------initListener---------------- IpcMessage class : " + ipcMessageCls);
    Class<?>[] interfaces = new Class[] { ListenerWrapper.getListenerClass() };
    logger.println("JenkinsSPListener - initListener - CustomClassLoader.getCustomClassLoader() : " + CustomClassLoader.getCustomClassLoader());
    return Proxy.newProxyInstance(CustomClassLoader.getCustomClassLoader(), interfaces, new InvocationHandler()
    {

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
      {
        String name = method.getName();

        if (name.equals("ltcTriggered"))
        {
          if (args.length != 1)
          {
            logger.println("ltcTriggered method has no arguments");
            return false;
          }
          Object evtIpcMessage = args[0];
          if (AgentMessageWrapper.getAgentMessageClass().isInstance(evtIpcMessage))
          {
            int msg = AgentMessageWrapper.getMsg(evtIpcMessage);
            int agent = AgentMessageWrapper.getAgent(evtIpcMessage);
            logger.print("Agent " + agent + " sent message ");
            if (msg == AgentMessageWrapper.getAttribute("MSG_Acknowledge"))
            {
              logger.println("Acknowledged");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Error"))
            {
              logger.println("Error");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Executed"))
            {
              logger.println("Executed");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Finished"))
            {
              logger.println("Finished");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Info"))
            {
              logger.println("Info");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Ping"))
            {
              logger.println("Ping");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Result"))
            {
              logger.println("Result");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Started"))
            {
              logger.println("Started");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Status"))
            {
              logger.println("Status");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_StatusProgress"))
            {
              logger.println("Status Progress");
            }
            else if (msg == AgentMessageWrapper.getAttribute("MSG_Warning"))
            {
              logger.println("Warning");
            }
            else
            {
              logger.println(msg);
            }
          }
          else if (ErrorMessageWrapper.getErrorMessageClass().isInstance(evtIpcMessage))
          {
            int errCode = ErrorMessageWrapper.getErrCode(evtIpcMessage);
            logger.println("Error message received " + errCode);
          }
          else if (LtcClientMessageWrapper.getLtcClientMessageClass().isInstance(evtIpcMessage))
          {
            int msgId = LtcClientMessageWrapper.getMessage(evtIpcMessage);
            int par1 = LtcClientMessageWrapper.getParam1(evtIpcMessage);
            int par2 = LtcClientMessageWrapper.getParam2(evtIpcMessage);
            String strPar = LtcClientMessageWrapper.getStrParam(evtIpcMessage);
            // Do we need this
            // logger.println("----------------LtcClientMessage: msgId=" + msgId + ", par1 = " + par1 + ", par2 = " + par2 + ", strPar = " + strPar);
          }
          else if (LtcInfoMessageWrapper.getLtcInfoMessageClass().isInstance(evtIpcMessage))
          {
            String compName = LtcInfoMessageWrapper.getComputerName(evtIpcMessage);
            int tlocal = LtcInfoMessageWrapper.getTimeLocal(evtIpcMessage);
            int tutc = LtcInfoMessageWrapper.getTimeUtc(evtIpcMessage);
            logger.println("LtcInfoMessage: comp=" + compName + ", times = " + tlocal + "," + tutc);
          }
          else if (LtcOutputMessageWrapper.getLtcOutputMessageClass().isInstance(evtIpcMessage))
          {
            int ipar = LtcOutputMessageWrapper.getIntParam(evtIpcMessage);
            String spar = LtcOutputMessageWrapper.getStringParam(evtIpcMessage);
            // Do we need this
            // logger.println("----------------LtcOutputMessage: ipar=" + ipar + ", spar = " + spar);
          }
          else if (VuAttributeMessageWrapper.getVuAttributeMessageClass().isInstance(evtIpcMessage))
          {
            int agent = VuAttributeMessageWrapper.getAgent(evtIpcMessage);
            int acmd = VuAttributeMessageWrapper.getAttCommand(evtIpcMessage);
            String attName = VuAttributeMessageWrapper.getAttName(evtIpcMessage);
            String attValue = VuAttributeMessageWrapper.getAttValue(evtIpcMessage);
            int vu = VuAttributeMessageWrapper.getVu(evtIpcMessage);
            String oldValue = null;
            if (VuAttributeChangedMessageWrapper.getVuAttributeChangedMessageClass().isInstance(evtIpcMessage))
            {
              oldValue = VuAttributeChangedMessageWrapper.getOldAttribute(evtIpcMessage);
              logger.print("VuAttributeChanged: ");
            }
            else
            {
              logger.print("VuAttribute: ");
            }
            logger.print("agent = " + agent + ", vu = " + vu + " cmd = " + acmd + " name = " + attName + " value = " + attValue);
            if (oldValue != null)
            {
              logger.println(" oldVal = " + oldValue);
            }
            else
            {
              logger.println();
            }
          }
          else if (VuContainerMessageWrapper.getVuContainerMessageClass().isInstance(evtIpcMessage))
          {
            int agent = VuContainerMessageWrapper.getAgent(evtIpcMessage);
            int err = VuContainerMessageWrapper.getError(evtIpcMessage);
            int firstVu = VuContainerMessageWrapper.getFirstVu(evtIpcMessage);
            int lastVu = VuContainerMessageWrapper.getLastVu(evtIpcMessage);
            int msgId = VuContainerMessageWrapper.getMsg(evtIpcMessage);
            int vucId = VuContainerMessageWrapper.getVuContainer(evtIpcMessage);
            logger.print("VuContainer " + vucId + " on " + agent + "(" + firstVu + "-" + lastVu + ") ");
            if (msgId == VuContainerMessageWrapper.getAttribute("MSG_Error"))
            {
              logger.println("reported err " + err);
            }
            else if (msgId == VuContainerMessageWrapper.getAttribute("MSG_Started"))
            {
              logger.println("started");
            }
            else if (msgId == VuContainerMessageWrapper.getAttribute("MSG_Finished"))
            {
              logger.println("finished");
            }
            else
            {
              logger.println("reporting unknown message " + msgId);
            }
          }
          else if (VuMessageWrapper.getVuMessageClass().isInstance(evtIpcMessage))
          {
            int agent = VuMessageWrapper.getAgent(evtIpcMessage);
            int msgId = VuMessageWrapper.getMsg(evtIpcMessage);
            int vu = VuMessageWrapper.getVu(evtIpcMessage);
            logger.print("VuMessage from " + vu + " on " + agent + " reported ");
            if (msgId == VuMessageWrapper.getAttribute("MSG_Error"))
            {
              logger.println("error");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Finished"))
            {
              logger.println("finished");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_FirstResult"))
            {
              logger.println("first result");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Info"))
            {
              logger.println("info");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_LastResult"))
            {
              logger.println("last result");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Result"))
            {
              logger.println("result");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Scheduled"))
            {
              logger.println("scheduled");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Started"))
            {
              logger.println("started");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Status"))
            {
              logger.println("status");
            }
            else if (msgId == VuMessageWrapper.getAttribute("MSG_Warning"))
            {
              logger.println("warning");
            }
            else
            {
              logger.println("unknown message " + msgId);
            }
          }
          else if (VuOutputMessageWrapper.getVuOutputMessageClass().isInstance(evtIpcMessage))
          {
            int agent = VuOutputMessageWrapper.getAgent(evtIpcMessage);
            // int color = VuOutputMessageWrapper.getColor(evtIpcMessage);
            String descLong = VuOutputMessageWrapper.getDescriptionLong(evtIpcMessage);
            String descShort = VuOutputMessageWrapper.getDescriptionShort(evtIpcMessage);
            int line = VuOutputMessageWrapper.getLineNo(evtIpcMessage);
            int type = VuOutputMessageWrapper.getOutputType(evtIpcMessage);
            int spix = VuOutputMessageWrapper.getScriptSpix(evtIpcMessage);
            int ts = VuOutputMessageWrapper.getTimestamp(evtIpcMessage);
            int vu = VuOutputMessageWrapper.getVu(evtIpcMessage);
            logger.println("Vu " + vu + " on " + agent + " in line " + line + "(" + spix + ") reported at " + ts);
            if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Custom"))
            {
              logger.println("custom message " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Data"))
            {
              logger.println("data " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Error"))
            {
              logger.println("error " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Function"))
            {
              logger.println("function " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Info"))
            {
              logger.println("info message " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_ProcExit"))
            {
              logger.println("procedure exit " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Timer"))
            {
              logger.println("timer " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Transaction"))
            {
              logger.println("transaction " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_TransExit"))
            {
              logger.println("transaction exit " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Warning"))
            {
              logger.println("warning " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Checkpoint_SUCCESS"))
            {
              logger.println("checkpoint(success) " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Checkpoint_WARNING"))
            {
              logger.println("checkpoint(warning) " + descShort + ", " + descLong);
            }
            else if (type == VuOutputMessageWrapper.getAttribute("VU_OUTPUT_Checkpoint_ERROR"))
            {
              logger.println("checkpoint(error) " + descShort + ", " + descLong);
            }
            else
            {
              logger.println("unknown message " + descShort + ", " + descLong);
            }
          }
          else if (VuSyncMessageWrapper.getVuSyncMessageClass().isInstance(evtIpcMessage))
          {
            int agent = VuSyncMessageWrapper.getAgent(evtIpcMessage);
            int scmd = VuSyncMessageWrapper.getSyncCommand(evtIpcMessage);
            int sid = VuSyncMessageWrapper.getSyncId(evtIpcMessage);
            String sname = VuSyncMessageWrapper.getSyncName(evtIpcMessage);
            int svalue = VuSyncMessageWrapper.getSyncValue(evtIpcMessage);
            int vu = VuSyncMessageWrapper.getVu(evtIpcMessage);
            logger.println("vu " + vu + " on " + agent + " synchronized " + sname + "," + svalue + "," + scmd + "," + sid);
          }
          else
          {
            logger.println("Message not handled - no appropriate event found " + evtIpcMessage.getClass());
          }
          return true;
        }

        throw new RuntimeException("no method found");
      }
    });
  }
}
