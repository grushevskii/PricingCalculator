package Fixtures;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless=true;
        Configuration.timeout=8000;
        Configuration.screenshots=false;
        Configuration.fastSetValue=true;
        Selenide.open("https://cloud.google.com/");
    }

}
