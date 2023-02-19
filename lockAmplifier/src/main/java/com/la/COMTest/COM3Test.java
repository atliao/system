package com.la.COMTest;

import com.la.port.PortController;
import gnu.io.SerialPort;

/**
 * @author LA
 * @createDate 2023-02-19-11:18
 */
public class COM3Test {

    public static void main(String[] args) {
        PortController portController = new PortController();
        SerialPort serialPort = portController.openPort("COM3");
        portController.ClosePort(serialPort);
    }
}
