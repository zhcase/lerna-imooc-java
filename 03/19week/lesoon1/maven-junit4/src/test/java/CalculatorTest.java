import com.imooc.junit.Calculator;
import org.junit.Test;

public class CalculatorTest {
    private Calculator cal=new Calculator();
//    1.与原方法保持一致
//    在原方法前增加test前辍
    @Test
    public  void testAdd(){

       int result= cal.add(1,2);
        System.out.println(result);
    }
}
