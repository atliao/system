package com.la;

import com.la.usb.USBController;

import javax.usb.*;

/**
 * @author LA
 * @createDate 2023-02-16-21:03
 */
public class SignalUsbController {

    private USBController usbController;
    private UsbDevice usbDevice;
    private UsbInterface iface;

    public void initController(int vid, int pid){
        usbController = new USBController();
        UsbHub rootUsbHub = null;
        try {
            rootUsbHub = UsbHostManager.getUsbServices().getRootUsbHub();
        } catch (UsbException e) {
            e.printStackTrace();
        }
        usbDevice = usbController.findDevice(rootUsbHub,vid, pid);
        iface = usbController.initInterface(usbDevice);
    }

    public void setChannelMode(int channel){
        String chan = "" + channel;
        String msg = Command.channel_mode.replace("<channel>", chan);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    public void setSignalPolarity(int channel){
        String chan = "" + channel;
        String msg = Command.signal_polarity.replace("<channel>", chan);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    public void setSignalScale(int channel){
        String chan = "" + channel;
        String msg = Command.signal_scale.replace("<channel>", chan);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    public void setSignalMode(int channel){
        String chan = "" + channel;
        String msg = Command.signal_mode.replace("<channel>", chan);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    //KHz
    public void setSignalFrequency(int channel, double freq){
        String chan = "" + channel;
        String frequency = "" + freq;
        String msg = Command.signal_cw_frequency.replace("<channel>", chan);
        msg = msg.replace("<freq>", frequency);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    //VPP
    public void setSignalAmplitude(int channel, double amp){
        String chan = "" + channel;
        String amplitude = "" + amp;
        String msg = Command.signal_amplitude.replace("<channel>", chan);
        msg = msg.replace("<amp>", amplitude);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    public void OutputOn(int channel){
        String chan = "" + channel;
        String msg = Command.channel_on.replace("<channel>", chan);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    public void OutputOff(int channel){
        String chan = "" + channel;
        String msg = Command.channel_off.replace("<channel>", chan);
        System.out.print(msg + "***************************************");
        //usbController.sendMessage(iface, msg);
    }

    public void closeController(){
        if(iface != null){
            try {
                iface.release();
            } catch (UsbException e) {
                e.printStackTrace();
            }
        }
    }

}
