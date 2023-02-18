package com.la.usb;

import javax.usb.*;
import java.util.List;

/**
 * @author LA
 * @createDate 2023-02-11-17:06
 */

//usb的操作

public class USBController {

    //十六进制转十进制
    public int hexadecimalToDecimal(String numsIn_16){
        int res = 0;
        int base = 1;
        char[] nums = numsIn_16.toCharArray();
        for(int i = nums.length - 1; i >=0; i-- ){
            if(nums[i] == 'x'){
                break;
            }
            int t;
            if(nums[i] >= '0' && nums[i] <= '9'){
                t = nums[i] - '0';
            }else{
                t = nums[i] - 55;
            }
            res += t * base;
            base *= 16;
        }
        return res;
    }

    public UsbDevice findDevice(UsbHub hub, int vid, int pid) {
        List<UsbDevice> list = hub.getAttachedUsbDevices();
        for (UsbDevice device:list) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vid && desc.idProduct() == pid) {
                byte b = desc.iSerialNumber();
                byte iProduct = desc.iProduct();
                return device;
            }
            if (device.isUsbHub()) {
                device= findDevice((UsbHub) device, vid, pid);
                if(device!=null){
                    return device;
                }
            }
        }
        return null;
    }


    public UsbInterface initInterface(UsbDevice device){
        UsbInterface iface = null;
        if (device.getActiveUsbConfiguration().getUsbInterfaces().size() > 0) {
            List usbInterfaces = device.getActiveUsbConfiguration().getUsbInterfaces();
            iface = (UsbInterface) usbInterfaces.get(0);
        }
        if (iface != null) {
            if(iface.isClaimed()==false){
                try {
                    iface.claim(usbInterface -> true);
                } catch (UsbException e) {
                    e.printStackTrace();
                }
            }
        }
        return iface;
    }

    public void closeInterface(UsbInterface iface) {
        try {
            iface.release();
        } catch (UsbException e) {
            e.printStackTrace();
        }
    }

    public int sendMessage(UsbInterface iface, String message){
        UsbEndpoint sendUsbEndpoint = (UsbEndpoint)iface.getUsbEndpoints().get(0);
        UsbPipe sendUsbPipe = sendUsbEndpoint.getUsbPipe();
        int submit = 0;
        try {
            if(sendUsbPipe.isOpen()==false){
                sendUsbPipe.open();
            }
            byte[] bytes = message.getBytes();
            submit = sendUsbPipe.syncSubmit(bytes);//阻塞
        } catch (UsbException e) {
            e.printStackTrace();
        }
        return submit;
    }

    public String receiveMessage(UsbInterface iface){
        byte[] bytes = new byte[16];
        int length = 0;
        UsbEndpoint receiveUsbEndpoint = (UsbEndpoint)iface.getUsbEndpoints().get(0);
        UsbPipe receiveUsbPipe = receiveUsbEndpoint.getUsbPipe();
        if(receiveUsbPipe.isOpen()==false){
            try {
                receiveUsbPipe.open();
            } catch (UsbException e) {
                e.printStackTrace();
            }
        }
        System.out.println("reading...");
        try {
            length = receiveUsbPipe.syncSubmit(bytes);
        } catch (UsbException e) {
            e.printStackTrace();
        }
        System.out.println("length: " + length);
        System.out.print("data: ");
        for (int i = 0; i < length; i++) {
            System.out.print(bytes[i] + " ");
        }
        System.out.println();

        String receiveMessage = new String(bytes);
        return receiveMessage;
    }


    /**
     * 以下为网络例子********************************************************************************************
     * @param device
     */

    // 初始化设备，请求获取、打开并接收数据
    public void initUseDevice(UsbDevice device){
        UsbInterface iface = null;
        if (device.getActiveUsbConfiguration().getUsbInterfaces().size() > 0) {
            List usbInterfaces = device.getActiveUsbConfiguration().getUsbInterfaces();
            iface = (UsbInterface) device.getActiveUsbConfiguration().getUsbInterfaces().get(0);
        }
        if (iface != null) {
            try {
                if(iface.isClaimed()==false){
                    iface.claim(usbInterface -> true);
                }
                UsbEndpoint receivedUsbEndpoint, sendUsbEndpoint;
                List usbEndpoints = iface.getUsbEndpoints();
                sendUsbEndpoint = (UsbEndpoint) iface.getUsbEndpoints().get(0);

                // 注意管道区别，OUT是usb设备输出数据的管道
                if (!sendUsbEndpoint.getUsbEndpointDescriptor().toString().contains("OUT")) {
                    receivedUsbEndpoint = sendUsbEndpoint;
                    //sendUsbEndpoint = (UsbEndpoint) iface.getUsbEndpoints().get(1);
                } else {
                    receivedUsbEndpoint = (UsbEndpoint) iface.getUsbEndpoints().get(1);
                }
                //发送
//                UsbPipe sendUsbPipe = sendUsbEndpoint.getUsbPipe();
//                if(sendUsbPipe.isOpen()==false){
//                    sendUsbPipe.open();
//                }
//                String str = "test";
//                send(sendUsbPipe,str.getBytes());

                //接收
                UsbPipe receivedUsbPipe = receivedUsbEndpoint.getUsbPipe();
                if(receivedUsbPipe.isOpen()==false){
                    receivedUsbPipe.open();
                }
                try {
                    receive(receivedUsbPipe);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 接收操作放到子线程实现异步回调
//                new Thread(() -> {
//                    try {
//                        receivedMassge(receivedUsbPipe);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                // 最后一定要关闭usb设备释放资源
                try {
                    iface.release();
                } catch (UsbException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 轮询读取USB发送回来的数据
    public void receive(UsbPipe usbPipe) throws Exception {
        byte[] buffer = new byte[64]; //规定的协议是64位
        int length;
        //while (true) {
            System.out.println("reading...");
            length = usbPipe.syncSubmit(buffer);
            System.out.println("******************************************result***************************************");
            System.out.println("length:" + length);

            String res = new String(buffer);
            System.out.println("string:" + res);

            for (int i = 0; i < length; i++) {
                // do something
                System.out.print(buffer[i]);
            }
            System.out.println();
        //}
    }

    // 发送数据
    public int send(UsbPipe usbPipe,byte[] buffer) throws UsbException {
        return usbPipe.syncSubmit(buffer);//阻塞
        //usbPipe.asyncSubmit(buff);//非阻塞
    }



}
