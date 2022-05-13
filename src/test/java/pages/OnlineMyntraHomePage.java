package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import listener.CucumberListener;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utilities.ConfigReader;
import utilities.ThreadLocalDriver;

public class OnlineMyntraHomePage {
  public OnlineMyntraHomePage(RemoteWebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  /**
   * Mobile Elements
   */
  @FindBy(xpath = "//*[text()='Women']")
  WebElement womenCategoryButton;

  public WebElement getWomenCategoryButton() {
    return womenCategoryButton;
  }

  @FindBy(xpath = "//*[text()='Kids']")
  WebElement kidsCategoryButton;

  public WebElement getKidsCategoryButton() {
    return kidsCategoryButton;
  }

  /**
   * Actions
   */
  public void clickStudio() {
    getKidsCategoryButton().click();
//    test.get().log(Status.INFO, "Clicked Studio", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Online()).build());
  }

  public void clickCategories() {
    getWomenCategoryButton().click();
//    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Online()).build());
  }

  public void userOnHomePage() {
    ConfigReader configReader = new ConfigReader();
    String onlineUrl = configReader.config().getProperty("OnlineUrl");
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Cloud").equalsIgnoreCase("true")) {
      ThreadLocalDriver.getTLDriverOnline().get(onlineUrl);
//      test.get().log(Status.INFO, "Home Page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Online()).build());
    } else {
      ThreadLocalDriver.getTLDriverOnlineLocal().get(onlineUrl);
//      test.get().log(Status.INFO, "Home Page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64OnlineLocal()).build());
    }
  }
}
