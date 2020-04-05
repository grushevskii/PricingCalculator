package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class GoogleCloudHomePage {

    private SelenideElement searchField=$(By.name("q"));

    @Step("Enter the name of the coupon in the search bar: {0} ")
    public GoogleCloudSearchPage pushAndInputSearchButton(String query){
        searchField.should(Condition.visible).click();
        searchField.setValue(query).pressEnter();
        return new GoogleCloudSearchPage();
    }

}
