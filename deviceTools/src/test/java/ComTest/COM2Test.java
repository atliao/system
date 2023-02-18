package ComTest;


import com.la.port.PortController;
import gnu.io.SerialPort;

/**
@author LA
@createDate 2023-02-18-10:28
*/

public class COM2Test {

    public static void main(String[] args) {
        PortController portController = new PortController();
        String test = "hello,COM3!";
        SerialPort port = portController.openPort("COM2");
        portController.sendmessage(port, test);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String readmessage = portController.readmessage(port);
        System.out.println("read:" + readmessage);
        portController.ClosePort(port);
    }
}
