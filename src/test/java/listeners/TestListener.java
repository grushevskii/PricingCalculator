package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;


public class TestListener  implements ITestListener {

  Logger log = Logger.getLogger(TestListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG (WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String saveTextLog (String message) {
        return message;
    }

    //HTML attachments for Allure
    public static void attachHtml(String link) {
        Allure.addAttachment("CurrentURL", "text/plain", link);
    }

    // Когда начинается тестовый пример, вызывается этот метод.
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
        log.info(getTestMethodName(iTestResult) + " test is starting.");
    }
//метод onTestSuccess вызывается при успешном выполнении любого теста.
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");

    }
//метод onTestFailure вызывается при сбое любого теста.
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");

        //Allure ScreenShot and SaveTestLog
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
        saveScreenshotPNG(getWebDriver());
        //Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        //Save a html on allure.
        attachHtml(url());
    }
//метод onTestSkipped вызывается при пропуске любого теста.
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
    }
//метод вызывается каждый раз, когда тест не пройден, но находится в пределах процента успеха.
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
//Вызывается после создания экземпляра тестового класса и перед вызовом  любого метода конфигурации.
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", getWebDriver());
    }
//вызывается после выполнения всех тестов и вызова всех их методов конфигурации.
    @Override
    public void onFinish  (ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }
}
