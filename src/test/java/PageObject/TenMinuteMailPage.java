package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TenMinuteMailPage {

    private ElementsCollection message=$$(".message_top");
    private SelenideElement table=$(By.xpath("//*[@class='quote']//tr[2]"));
    private SelenideElement mailAddressField=$("#mail_address");
    private static String email;

    public static String getAddress(){
        return email;
    }

    public PricingCalculatorPage switchToPage(){
        switchTo().window(0);
        return new PricingCalculatorPage();
    }

    public TenMinuteMailPage getMailAddress(){
       email=mailAddressField.should(visible).getValue();
        return this;
    }
    @Step("Click Message")
    public TenMinuteMailPage pushMessage(){
        message.first().should(visible).click();
        return this;
    }
    @Step("Verification total cost components")
    public void verificationTotalCostComponents(String name,String component){
        table.find("td",0).find("h3").shouldHave(text(name));
        table.find("td",1).find("h3").shouldHave(text(component));
    }
}
