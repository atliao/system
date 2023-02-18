package com.la.port;

import gnu.io.SerialPort;

/**
 * @author LA
 * @createDate 2023-02-18-18:36
 */
public class PortParameter {

    private String PortName; //串口名称(COM0、COM1、COM2等等)

    private int baudRate; //波特率

    private int dataBits; //数据位 可以设置的值：SerialPort.DATABITS_5、SerialPort.DATABITS_6、SerialPort.DATABITS_7、SerialPort.DATABITS_8

    private int stopBits; //停止位 可以设置的值：SerialPort.STOPBITS_1、SerialPort.STOPBITS_2、SerialPort.STOPBITS_1_5

    private int parity; //校验位 可以设置的值：SerialPort.PARITY_NONE、SerialPort.PARITY_ODD、SerialPort.PARITY_EVEN、SerialPort.PARITY_MARK、SerialPort.PARITY_SPACE

    public PortParameter() {
        this.PortName = null; //名称
        this.baudRate = 9600; //波特率9600
        this.dataBits = SerialPort.DATABITS_8; //数据位默认8位
        this.stopBits = SerialPort.STOPBITS_1; //停止位默认1位
        this.parity = SerialPort.PARITY_NONE; //校验位默认0位
    }

    //获取和更改串口名
    public String getPortName() {
        return PortName;
    }

    public void setPortName(String PortName) {
        this.PortName = PortName;
    }

    //获取和更改波特率
    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    //获取和更改数据位
    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    //获取和更改停止位
    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    //获取和更改校验位
    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }
}
