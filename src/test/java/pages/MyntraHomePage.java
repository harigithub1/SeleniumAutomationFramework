package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import listener.CucumberListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MyntraHomePage {
  public MyntraHomePage(AppiumDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  /**
   * Mobile Elements
   */
  @AndroidFindBy(xpath = "//*[@text='Studio']")
  @iOSXCUITFindBy(xpath = "//*[@text='Studio']")
  MobileElement studio;

  public MobileElement getStudio() {
    return studio;
  }

  @AndroidFindBy(xpath = "//*[@text='Profile']")
  @iOSXCUITFindBy(xpath = "//*[@text='Profile']")
  MobileElement Profile;

  public MobileElement getProfile() {
    return Profile;
  }

  @AndroidFindBy(xpath = "//*[@text='Home']")
  @iOSXCUITFindBy(xpath = "//*[@text='Home']")
  MobileElement Home;

  public MobileElement getHome() {
    return Home;
  }

  @AndroidFindBy(xpath = "//*[@text='Explore']")
  @iOSXCUITFindBy(xpath = "//*[@text='Explore']")
  MobileElement Explore;

  public MobileElement getExplore() {
    return Explore;
  }

  @AndroidFindBy(xpath = "//*[@text='Categories']")
  @iOSXCUITFindBy(xpath = "//*[@text='Categories']")
  MobileElement Categories;

  public MobileElement getCategories() {
    return Categories;
  }

  @AndroidFindBy(xpath = "//*[@text='Explore']")
  @iOSXCUITFindBy(xpath = "//*[@text='Explore']")
  MobileElement ExploreTitle;

  public MobileElement getExploreTitle() {
    return ExploreTitle;
  }

  /**
   * Actions
   */
  public void clickStudio() {
    getStudio().click();
    //Thread.sleep(3000);
//    test.get().log(Status.INFO, "Clicked Studio", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickProfile() {
    getProfile().click();
//    test.get().log(Status.INFO, "Clicked Profile", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickHome() {
    getHome().click();
//    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickExplore() {
    getExplore().click();
//    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickCategories() {
    getCategories().click();
//    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void userOnHomePage() {
//    test.get().log(Status.INFO, "Home Page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void assertExploreTitleIsExpected() {
//    test.get().log(Status.INFO, "Verify Explore button", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
    Assert.assertTrue(getExploreTitle().isDisplayed());
  }
}
