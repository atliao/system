package UsbTest;

import com.la.usb.USBController;
import org.junit.Test;

import javax.usb.*;

/**
 * @author LA
 * @createDate 2023-02-11-17:15
 */
public class UsbControllerTest {

    USBController usbController = new USBController();

    @Test
    public void test(){
        int a = 0x0d;
        System.out.println(a);
        String s = "0x0D4A";
        int res = usbController.hexadecimalToDecimal(s);
        System.out.println(res);
    }

    @Test
    public void findDeviceTest(){
        UsbHub rootUsbHub = null;
        try {
            rootUsbHub = UsbHostManager.getUsbServices().getRootUsbHub();
        } catch (UsbException e) {
            e.printStackTrace();
        }
        UsbDevice device = usbController.findDevice(rootUsbHub, 0x12D1, 0x107E);
        System.out.println(device);
        System.out.println("*****************");
    }

    @Test
    public void sendTest(){
        UsbHub rootUsbHub = null;
        try {
            rootUsbHub = UsbHostManager.getUsbServices().getRootUsbHub();
        } catch (UsbException e) {
            e.printStackTrace();
        }
        UsbDevice device = usbController.findDevice(rootUsbHub, 0x12D1, 0x107E);
        UsbInterface iface = usbController.initInterface(device);
        int length = usbController.sendMessage(iface, "test");
        System.out.println(length);
        usbController.closeInterface(iface);
    }

    @Test
    public void receiveTest(){
        UsbHub rootUsbHub = null;
        try {
            rootUsbHub = UsbHostManager.getUsbServices().getRootUsbHub();
        } catch (UsbException e) {
            e.printStackTrace();
        }
        UsbDevice device = usbController.findDevice(rootUsbHub, 0x2717, 0x5010);
        UsbInterface iface = usbController.initInterface(device);
        String receive = usbController.receiveMessage(iface);
        System.out.println("***" + receive + "***");
        usbController.closeInterface(iface);
    }

}
