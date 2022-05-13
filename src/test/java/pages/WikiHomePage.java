package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import listener.CucumberListener;
import lombok.SneakyThrows;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WikiHomePage {
  public WikiHomePage(AppiumDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"My lists\"]/android.view.ViewGroup")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Alert\"]")
  MobileElement ListBy;

  public MobileElement getListBy() {
    return ListBy;
  }

  @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"History\"]/android.widget.ImageView")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"OK\"]")
  MobileElement HistoryOrOk;

  public MobileElement getHistoryBy() {
    return HistoryOrOk;
  }

  @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Nearby\"]/android.view.ViewGroup")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Text Button\"]")
  MobileElement NearOrTextBtn;

  public MobileElement getNearOrTextBtn() {
    return NearOrTextBtn;
  }

  @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Explore\"]/android.view.ViewGroup")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Text Input\"]")
  MobileElement ExploreOrTextInpt;

  public MobileElement getExploreOrTextInpt() {
    return ExploreOrTextInpt;
  }

  @AndroidFindBy(xpath = "//*[@text='In the news']")
  @iOSXCUITFindBy(xpath = "somexpath")
  MobileElement InTheNewsTitleBy;

  public MobileElement getInTheNewsTitleBy() {
    return InTheNewsTitleBy;
  }

  /**
   * Actions
   */
  @SneakyThrows
  public void clickListElement() {
    Thread.sleep(2000);
    getListBy().click();
//    test.get().log(Status.INFO, "Clicked List/Alert", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  @SneakyThrows
  public void clickHistoryElement() {
    Thread.sleep(2000);
    getHistoryBy().click();
//    test.get().log(Status.INFO, "Clicked History/OK", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  @SneakyThrows
  public void clickNearbyElement() {
    Thread.sleep(2000);
    getNearOrTextBtn().click();
//    test.get().log(Status.INFO, "Clicked Nearby/Text box", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  @SneakyThrows
  public void clickExploreElement() {
    Thread.sleep(2000);
    getExploreOrTextInpt().click();
//    test.get().log(Status.INFO, "Clicked Explore/Test input", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  @SneakyThrows
  public void assertInTheNewsTitleIsAsExpected() {
    Thread.sleep(5000);
    Assert.assertTrue(getInTheNewsTitleBy().isDisplayed());
//    test.get().log(Status.INFO, "InTheNews", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  @SneakyThrows
  public void userOnWikiHomePage() {
    //  Thread.sleep(3000);
//    test.get().log(Status.INFO, "Verify Explore button", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }
}