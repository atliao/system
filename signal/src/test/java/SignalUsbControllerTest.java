import com.la.SignalUsbController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-02-17-12:49
 */
public class SignalUsbControllerTest {

    SignalUsbController signalUsbController = new SignalUsbController();

    @Test
    public void testOutPOn(){
        //signalController.initController(0x2717,0x5010);
        signalUsbController.OutputOn(1);
    }


    @Test
    public void testOutPOff(){
        //signalController.initController(0x2717,0x5010);
        signalUsbController.OutputOff(1);
    }

    @Test
    public void process(){
        signalUsbController.setChannelMode(1);
        System.out.println();
        signalUsbController.setSignalPolarity(1);
        System.out.println();
        signalUsbController.setSignalScale(1);
        System.out.println();
        signalUsbController.setSignalMode(1);
        System.out.println();
        signalUsbController.setSignalFrequency(1,2.4);
        System.out.println();
        signalUsbController.setSignalAmplitude(1,2.0);
        System.out.println();
        signalUsbController.OutputOn(1);
        System.out.println();
        signalUsbController.OutputOff(1);
        System.out.println();
    }
}
