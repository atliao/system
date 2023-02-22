package com.la;

import com.la.VISA.VISAController;

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

    //初始化信道
    public void initSignalChannel(int channel) throws Exception{
        //信道独立
        String chan1 = "" + channel;
        String msg1 = SCommand.channel_mode.replace("<channel>", chan1);
        visaController.writeCmd(msg1);

        Thread.sleep(360);

        //信号波形及反转：正弦，正常（不反转）
        String chan2 = "" + channel;
        String msg2 = SCommand.signal_polarity.replace("<channel>", chan2);
        visaController.writeCmd(msg2);

        Thread.sleep(120);

        //信号极性：FS，双极性
        String chan3 = "" + channel;
        String msg3 = SCommand.signal_scale.replace("<channel>", chan3);
        visaController.writeCmd(msg3);

        Thread.sleep(120);

        //信号模式：连续
        String chan4 = "" + channel;
        String msg4 = SCommand.signal_mode.replace("<channel>", chan4);
        visaController.writeCmd(msg4);

        Thread.sleep(100);
    }


    //信号频率：KHz
    public void setSignalFrequency(int channel, double freq){
        String chan = "" + channel;
        String frequency = "" + freq;
        String msg = SCommand.signal_cw_frequency.replace("<channel>", chan);
        msg = msg.replace("<freq>", frequency);
        visaController.writeCmd(msg);
    }

    //信号振幅：VPP
    public void setSignalAmplitude(int channel, double amp){
        String chan = "" + channel;
        String amplitude = "" + amp;
        String msg = SCommand.signal_amplitude.replace("<channel>", chan);
        msg = msg.replace("<amp>", amplitude);
        visaController.writeCmd(msg);
    }

    //信号输出开启
    public void OutputOn(int channel){
        String chan = "" + channel;
        String msg = SCommand.channel_on.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    //信号输出关闭
    public void OutputOff(int channel){
        String chan = "" + channel;
        String msg = SCommand.channel_off.replace("<channel>", chan);
        visaController.writeCmd(msg);
    }

    //关闭设备接口
    public void closeController(){
        visaController.close();
    }
}
