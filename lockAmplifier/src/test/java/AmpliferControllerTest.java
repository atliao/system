import com.la.AmplifierController;
import org.junit.Test;

/**
 * @author LA
 * @createDate 2023-02-21-12:51
 */
public class AmpliferControllerTest {

    AmplifierController amplifierController = new AmplifierController("COM2");

    @Test
    public void testProcess() throws Exception{

        //查询ID
        String id = amplifierController.QueryID();
        System.out.println("设备id: " + id);

        Thread.sleep(100);

        //查询参考源
        String refrence = amplifierController.QueryRefrence();
        System.out.println("参考源refrence: " + refrence);

        Thread.sleep(100);

        //设置参考源
        amplifierController.setRefrence(0);

        Thread.sleep(100);

        //设置参考源相位
        amplifierController.setRefrencePash(0);

        Thread.sleep(100);

        //设置参考源频率
        amplifierController.setRefrenceFreq(1000);

        Thread.sleep(100);

        //查询输出正弦信号振幅
        String sineAmplitude = amplifierController.QuerySineAmplitude();
        Double sineA = Double.valueOf(sineAmplitude);
        System.out.println("输出正弦信号振幅sineAmplitude: " + sineA + " V");
        System.out.println();

        Thread.sleep(100);

        //设置输出正弦信号振幅
        amplifierController.setSineAmplitude(3.0);

        Thread.sleep(100);

        //查询输入源模式
        String sourceIn = amplifierController.QuerySourceIn();
        System.out.println("输入源模式sourceIn: " + sourceIn);

        Thread.sleep(100);

        //设置输入源模式
        amplifierController.setSourceIn(0);

        Thread.sleep(100);

        //查询输入信号的振幅（V）
        String sourceAmplitude = amplifierController.QuerySourceAmplitude();
        Double sourceA = Double.valueOf(sourceAmplitude);
        System.out.println("输入信号振幅sourceAmplitude: " + sourceA + " V");
        System.out.println();

        Thread.sleep(100);

        //查询输出情况
        String outSignal = amplifierController.QueryOutSignal(1);
        System.out.println("CH1输出情况outSignal: " + outSignal);

        Thread.sleep(100);

        //设置输出情况
        amplifierController.setOutSignal(1, 1);

        Thread.sleep(100);

        //查询控制功能
        String loclalFounction = amplifierController.QueryLoclalFounction();
        System.out.println("控制模式localFounction: " + loclalFounction);

        Thread.sleep(100);

        //设置控制功能
        amplifierController.setLocalFounction(1);

        Thread.sleep(100);

        //关闭接口
        amplifierController.closePort();

    }
}
