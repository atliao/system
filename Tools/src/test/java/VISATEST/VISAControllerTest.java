package VISATEST;

import com.la.VISA.VISAController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-02-19-11:15
 */
public class VISAControllerTest {

    @Test
    public void testOpen(){
        VISAController vController = new VISAController();
        boolean open = vController.open("ASRL2::INSTR");
        System.out.println(open);
        boolean close = vController.close();
        System.out.println(close);
    }

    @Test
    public void testSendAndRead(){
        VISAController vController = new VISAController();
        boolean open = vController.open("ASRL2::INSTR");
        System.out.println(open);
        vController.writeCmd("*IDN?");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = vController.readResult();
        System.out.println(s);
        boolean close = vController.close();
        System.out.println(close);
    }
}
