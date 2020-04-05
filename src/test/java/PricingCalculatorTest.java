import Fixtures.BaseTest;
import PageObject.GoogleCloudHomePage;
import PageObject.PricingCalculatorPage;
import PageObject.TenMinuteMailPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import providers.DataProviderClass;

@Listeners({ TestListener.class })
@Feature("Pricing Calculator Tests")
public class PricingCalculatorTest extends BaseTest {

    @Test(alwaysRun = true, priority =1,
            dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test add to estimate and verification data")
    public void successAddToEstimateCalculatorAndVerificationData(String query,String valueInstances,String vmClass,String valueVMClass,
                                                                  String instanceType, String valueInstanceType,String localSSD,
                                                                  String valueLocalSSD, String valueRegion,
                                                                  String valueCommittedUsage,String valueCost)
    {
        new GoogleCloudHomePage()
                .pushAndInputSearchButton(query)
                .pushCalculatorResult(query)
                .pushComputeEngineTab()
                .inputInstances(valueInstances)
                .clearWhatAreTheseInstances()
                .choiceOperatingSystem("Operating System",
                        "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .choiceVMClassAndUsage(vmClass,valueVMClass)
                .choiceOtherItem(instanceType,"typeInfo",valueInstanceType)
                .choiceAddGPU()
                .choiceOtherItem("Number of GPUs","GpuNumbers","1")
                .choiceOtherItem("GPU type","gpuList","NVIDIA Tesla P4")
                .choiceOtherItem(localSSD,"supportedSsd",valueLocalSSD)
                .choiceOtherItem("Datacenter location","fullRegionList",valueRegion)
                .choiceVMClassAndUsage("Committed usage",valueCommittedUsage)
                .pushAddEstimate()
                .verificationValueComponents(vmClass,valueVMClass)
                .verificationValueComponents(instanceType,valueInstanceType)
                .verificationValueComponents("Region",valueRegion)
                .verificationValueComponents(localSSD,valueLocalSSD)
                .verificationValueComponents("Commitment term",valueCommittedUsage)
                .verificationTotalCostComponents("Total Estimated Cost",valueCost);
    }

    @Test(alwaysRun = true, priority =2,
            dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test email to estimate and verification data")
    public void successEmailEstimateCalculatorAndVerificationData(String valueCost){
                 new PricingCalculatorPage()
                         .pushEmailEstimate()
                         .openPage()
                         .switchToPage()
                         .getMailAddress()
                         .switchToPage()
                         .inputFieldEmail(TenMinuteMailPage.getAddress())
                         .pushSendEmail()
                         .switchToPage()
                         .pushMessage()
                         .verificationTotalCostComponents("Total Estimated Monthly Cost",valueCost);
    }

}
