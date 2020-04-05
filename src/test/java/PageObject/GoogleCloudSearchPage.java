package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class GoogleCloudSearchPage {

    private SelenideElement resultsSearch=$(".gs-webResult");

    @Step("Click \"Google Cloud Platform Pricing Calculator\" and go to the calculator page")
    public PricingCalculatorPage pushCalculatorResult(String query){
        resultsSearch.find(byText(query)).should(visible).click();
        return new PricingCalculatorPage();
    }

}
