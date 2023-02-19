package com.la.port;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

/**
 * @author LA
 * @createDate 2023-02-19-11:12
 */
public class PortController {

    //定义串口参数对象
    static PortParameter parameter = new PortParameter();

    @SuppressWarnings("unchecked")

    //获得系统可用的端口名称列表(COM0、COM1、COM2等等)

    public List<String> getPortList() {
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名字
            systemPorts.add(portName);
        }
        return systemPorts; //@return List<String>可用端口名称列表
    }


    /**
     * 初始化串口(引入串口参数)并抛出可能的错误
     *
     * @param PortName 串口名称
     * @return SerialPort 串口对象
     * @throws NoSuchPortException               对应串口不存在
     * @throws PortInUseException                串口在使用中
     * @throws UnsupportedCommOperationException 不支持操作操作
     */

    public static SerialPort initPort(String PortName) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        parameter.setPortName(PortName);
        return SetPort(parameter);
    }

    /**
     * 设置串口参数
     *
     * @param parameter 串口参数
     * @return SerialPort串口对象
     * @throws NoSuchPortException               对应串口不存在
     * @throws PortInUseException                串口在使用中
     * @throws UnsupportedCommOperationException 不支持操作操作
     */
    public static SerialPort SetPort(PortParameter parameter)
            throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException {
        //通过端口名称得到端口
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(parameter.getPortName());
        //打开端口，（自定义名字，打开超时时间）
        int timeout = 2000; //超时时间2000ms
        CommPort commPort = portIdentifier.open(parameter.getPortName(), timeout);
        //判断是不是串口
        if (commPort instanceof SerialPort) {
            SerialPort serialPort = (SerialPort) commPort;
            //设置串口参数（波特率，数据位8，停止位1，校验位无）
            serialPort.setSerialPortParams(parameter.getBaudRate(), parameter.getDataBits(), parameter.getStopBits(), parameter.getParity());
            System.out.println(parameter.getPortName()+" open successfully!");
            return serialPort;
        } else {
            //是其他类型的端口
            throw new NoSuchPortException();
        }
    }

    //打开串口，指定打开串口的名称，如COM1，COM2
    //捕捉之前initPort函数抛出的错误
    //返回串口对象
    public SerialPort openPort(String port) {
        SerialPort serialPort = null;
        try {
            serialPort = initPort(port);
        }catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException e) {
            e.printStackTrace();
        }
        return serialPort;
    }
    /**
     * 关闭串口
     *
     * @param serialPort 要关闭的串口对象
     */
    public  void ClosePort(SerialPort serialPort) {
        if (serialPort != null) {
            serialPort.close();
            System.out.println(parameter.getPortName()+" is closed");
        }
    }

    /**
     * 向串口发送数据//发比特
     *
     * @param serialPort 串口对象
     * @param data       发送的数据
     */
    public static void sendData(SerialPort serialPort, byte[] data) {
        OutputStream os = null;
        try {
            //获得串口的输出流
            os = serialPort.getOutputStream();
            os.write(data);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从串口读取数据//读比特
     *
     * @param serialPort 要读取的串口
     * @return 读取的数据
     */
    public static byte[] readData(SerialPort serialPort) {
        InputStream is = null;
        byte[] bytes = null;
        try {
            //获得串口的输入流
            is = serialPort.getInputStream();
            //获得数据长度
            int bufflenth = is.available();
            while (bufflenth != 0) {
                //初始化byte数组
                bytes = new byte[bufflenth];
                is.read(bytes);
                bufflenth = is.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
    //发送消息：字符串
    public void sendmessage(SerialPort serialPort, String message) {
        byte[] bytes = message.getBytes();
        sendData(serialPort, bytes);
    }
    //接收消息：字符串
    public String readmessage(SerialPort serialPort) {
        String message;
        byte[] bytes = readData(serialPort);
        if(bytes==null) {
            message = "";
        }
        else {
            message = new String(bytes);
        }
        return message;
    }

    /**
     * 给串口设置监听
     *
     * @param serialPort serialPort 要读取的串口
     * @param listener   SerialPortEventListener监听对象
     * @throws TooManyListenersException 监听对象太多
     */
    public void setListenerToSerialPort(SerialPort serialPort, SerialPortEventListener listener) throws TooManyListenersException {
        //给串口添加事件监听
        serialPort.addEventListener(listener);
        //串口有数据监听
        serialPort.notifyOnDataAvailable(true);
        //中断事件监听
        serialPort.notifyOnBreakInterrupt(true);
    }
}
