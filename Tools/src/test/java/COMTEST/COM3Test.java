package COMTEST;

import com.la.port.PortController;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;

import java.util.Calendar;
import java.util.TooManyListenersException;

/**
 * @author LA
 * @createDate 2023-02-19-11:15
 */
public class COM3Test {

    private Thread threadA;

    public COM3Test() {
        PortController portController = new PortController();
        SerialPort serialport = portController.openPort("COM3");

        threadA = new Thread(new Runnable() {
            public void run() { // 重写run()方法
                try {

                    portController.setListenerToSerialPort(serialport, event -> {
                        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                            Calendar Cld = Calendar.getInstance();
                            int YY = Cld.get(Calendar.YEAR);
                            int MM = Cld.get(Calendar.MONTH) + 1;
                            int DD = Cld.get(Calendar.DATE);
                            int HH = Cld.get(Calendar.HOUR_OF_DAY);
                            int mm = Cld.get(Calendar.MINUTE);
                            int SS = Cld.get(Calendar.SECOND);
                            int MI = Cld.get(Calendar.MILLISECOND);
                            String curTime = YY + "-" + MM + "-" + DD + "," + HH + ":" + mm + ":" + SS + ":" + MI;

                            String str = portController.readmessage(serialport);
                            System.out.println(curTime + "-> " + str);
                            if(str.equals("*IDN?\n")){
                                portController.sendmessage(serialport,"ID: 15184335457\n");
                            }
                            if(str.equals("FMOD?\n")){
                                portController.sendmessage(serialport, "0\n");
                            }
                            if(str.equals("SLVL?\n")){
                                portController.sendmessage(serialport,"2.0\n");
                            }
                            if(str.equals("ISRC?\n")){
                                portController.sendmessage(serialport, "0\n");
                            }
                            if(str.equals("OUTP? 3\n")){
                                portController.sendmessage(serialport, "2.14567\n");
                            }
                            if(str.equals("FOUT? 1\n")){
                                portController.sendmessage(serialport, "1\n");
                            }
                            if(str.equals("LOCL?\n")){
                                portController.sendmessage(serialport, "1\n");
                            }
                        }
                    });
                }catch (TooManyListenersException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
    }

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        new COM3Test();
    }
}
