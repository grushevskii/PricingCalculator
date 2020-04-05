package PageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.By.xpath;

public class PricingCalculatorPage {

    private SelenideElement computeEngineTab=$(byText("Compute Engine"));
    private SelenideElement frameDefault=$(xpath("//*[@id='cloud-site']//iframe"));
    private SelenideElement frame=$(By.id("myFrame"));
    private SelenideElement instances=$(xpath("//label[contains(text(),'Number of instances')]//following-sibling::input"));
    private SelenideElement whatAreTheseInstances=$(xpath("//label[contains(text(),' instances for?')]//following-sibling::input"));
    private ElementsCollection selectContainer=$$(xpath("//div[contains(@class,'md-active')]//div"));
    private SelenideElement checkboxAddGPUs=$(xpath("//div[contains(text(),'Add GPUs')]//preceding-sibling::div"));
    private SelenideElement addEstimate=$(byText("Add to Estimate"));
    private ElementsCollection components=$$(xpath("//*[@class='md-list-item-text ng-binding']"));
    private ElementsCollection totalEstimatedCost=$$(".md-title b");
    private SelenideElement emailEstimate=$(byText("Email Estimate"));
    private SelenideElement fieldEmail=$(xpath("//input[@type='email']"));
    private SelenideElement sendEmailButton=$(byText("Send Email"));

    @Step("Activate the COMPUTE ENGINE section at the top of the page")
    public PricingCalculatorPage pushComputeEngineTab(){
        switchToFrame();
        executeJavaScript("arguments[0].click();",computeEngineTab);
        return this;
    }
    @Step("")
    public PricingCalculatorPage openPage(){
        ((JavascriptExecutor)getWebDriver()).executeScript("window.open(\"https://10minutemail.com/\");");
        return this;
    }

  public PricingCalculatorPage switchToFrame(){
     switchTo().frame(frameDefault)
              .switchTo().frame(frame);
      return this;
  }

    public TenMinuteMailPage switchToPage(){
        switchTo().window(1);
        return new TenMinuteMailPage();
    }
    @Step("Enter the number in the number of instances field: {0}")
    public PricingCalculatorPage inputInstances(String number){
        instances.click();
        instances.setValue(number);
        return this;
    }
    @Step("Clear field what are these instances for?")
    public PricingCalculatorPage clearWhatAreTheseInstances(){
        whatAreTheseInstances.click();
        whatAreTheseInstances.clear();
        return this;
    }
    @Step("Enter the value {1} in the field {0}")
    public PricingCalculatorPage choiceOperatingSystem(String item,String component){
        executeJavaScript("arguments[0].click();", $(xpath(String.format("//*[contains(@aria-label,'%s')]",item))));
        executeJavaScript("arguments[0].click();",selectContainer.filter(appears).find(text(component)).hover());
        return this;
    }
    @Step("Choose Add GPUs")
    public PricingCalculatorPage choiceAddGPU(){
        executeJavaScript("arguments[0].scrollIntoView(true),arguments[0].click();",checkboxAddGPUs);
        return this;
    }
    @Step("Enter the value {1} in the field {0}")
    public PricingCalculatorPage choiceVMClassAndUsage(String item,String component){
        executeJavaScript("arguments[0].click();", $(xpath(String.format("//*[@placeholder='%s']",item))));
        executeJavaScript("arguments[0].scrollIntoView(true),arguments[0].click();",
                selectContainer.filter(appears).find(text(component)));
        return this;
    }
    @Step("Enter the value {2} in the field {0}")
    public PricingCalculatorPage choiceOtherItem(String item,String choice,String component){
        executeJavaScript("arguments[0].click();", $(xpath(String.format("//*[@placeholder='%s']",item))));
        executeJavaScript("arguments[0].scrollIntoView(true),arguments[0].click();",
                $$(xpath(String.format("//*[contains(@ng-repeat,'%s')]",choice))).filter(appears).find(text(component)));
        return this;
    }
    @Step("Click Add to Estimate")
    public PricingCalculatorPage pushAddEstimate(){
        executeJavaScript("arguments[0].click();",addEstimate);
        return this;
    }
    @Step("Verification value component {0}")
    public PricingCalculatorPage verificationValueComponents(String name,String component){
       components.findBy(text(name)).shouldHave(text(component));
       return this;
    }
    @Step("Verification total cost components")
    public void verificationTotalCostComponents(String name,String cost){
        totalEstimatedCost.find(text(name)).shouldHave(text(cost));
    }
    @Step("Click Email Estimate")
    public PricingCalculatorPage pushEmailEstimate(){
        executeJavaScript("arguments[0].click();",emailEstimate);
        return this;
    }
    @Step("Enter the value {0} in the field Email")
    public PricingCalculatorPage inputFieldEmail(String email){
        switchToFrame();
        executeJavaScript("arguments[0].scrollIntoView(true),arguments[0].click();",fieldEmail);
        fieldEmail.setValue(email);
        return this;
    }
    @Step("Click Send Email")
    public PricingCalculatorPage pushSendEmail(){
        executeJavaScript("arguments[0].click();",sendEmailButton);
        return this;
    }
}
