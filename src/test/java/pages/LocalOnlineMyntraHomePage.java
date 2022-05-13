package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import listener.CucumberListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utilities.ThreadLocalDriver;

public class LocalOnlineMyntraHomePage {
  public LocalOnlineMyntraHomePage(WebDriver driver) {
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
//    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64OnlineLocal()).build());
  }

  public void userOnHomePage() {
      ThreadLocalDriver.getTLDriverOnlineLocal().get("https://www.myntra.com/");
//      test.get().log(Status.INFO, "Home Page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64OnlineLocal()).build());
  }
}
