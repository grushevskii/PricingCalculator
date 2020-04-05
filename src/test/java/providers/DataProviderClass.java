package providers;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;


//Данный класс является поставщиком данных для тестовых методов
public class DataProviderClass
{
  //Данная функция позволяет передать различные параметры в зависимости от имени метода теста
    @DataProvider(name = "data-provider")
    public static Object[][]provideData(Method method) {
        Object[][]result = null;
        if (method.getName().equals("successAddToEstimateCalculatorAndVerificationData")) {
            result = new Object[][]{
                    { "Google Cloud Platform Pricing Calculator",String.valueOf(4),"VM Class","Regular","Instance type",
                            "n1-standard-8","Local SSD","2x375 GB","Frankfurt","1 Year","USD 1,082.77"}
            };
        } else if (method.getName().equals("successEmailEstimateCalculatorAndVerificationData")) {
            result = new Object[][]{
                    {"USD 1,082.77"}
            };
        }
        return result;
    }

}
