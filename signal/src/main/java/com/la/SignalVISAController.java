package com.la;

import com.la.USB.USBController;
import com.la.VISA.VISAController;

import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;

/**
 * @author LA
 * @createDate 2023-02-19-11:34
 */
public class SignalVISAController {

    VISAController visaController = new VISAController();

    public void initController(String ip){
        visaController.open(ip);
    }

    //查询ID
    public String readID(){
        visaController.writeCmd("*IDN?");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = visaController.readResult();
        return res;
    }
    //信道独立
    public void setChannelMode(int channel){
        String chan = "" + channel;
        String msg = Command.channel_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    //信号波形及反转：正弦，正常（不反转）
    public void setSignalPolarity(int channel){
        String chan = "" + channel;
        String msg = Command.signal_polarity.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    //信号极性：FS，双极性
    public void setSignalScale(int channel){
        String chan = "" + channel;
        String msg = Command.signal_scale.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }


    //信号模式：连续
    public void setSignalMode(int channel){
        String chan = "" + channel;
        String msg = Command.signal_mode.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    //信号频率：KHz
    public void setSignalFrequency(int channel, double freq){
        String chan = "" + channel;
        String frequency = "" + freq;
        String msg = Command.signal_cw_frequency.replace("<channel>", chan);
        msg = msg.replace("<freq>", frequency);
        visaController.writeCmd(msg);
    }

    //信号振幅：VPP
    public void setSignalAmplitude(int channel, double amp){
        String chan = "" + channel;
        String amplitude = "" + amp;
        String msg = Command.signal_amplitude.replace("<channel>", chan);
        msg = msg.replace("<amp>", amplitude);
        visaController.writeCmd(msg);
    }

    //信号输出开启
    public void OutputOn(int channel){
        String chan = "" + channel;
        String msg = Command.channel_on.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    //信号输出关闭
    public void OutputOff(int channel){
        String chan = "" + channel;
        String msg = Command.channel_off.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    public void closeController(){
        visaController.close();
    }
}
