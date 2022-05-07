package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;

public class MyntraHomePage extends BasePage {
  public MyntraHomePage(AppiumDriver<MobileElement> driver) {
    super(driver);
  }

  /**
   * Mobile Elements
   */
  By allowWhenUsingBy = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
  By jobsBy = By.id("com.isinolsun.app:id/rootRelativeView");
  By profileIconBy = By.xpath("//android.widget.LinearLayout[4]/android.widget.ImageView");

  By StudioBy = By.xpath("//*[@text='Studio']");
  By ProfileBy = By.xpath("//*[@text='Profile']");
  By HomeBy = By.xpath("//*[@text='Home']");
  By ExploreBy = By.xpath("//*[@text='Explore']");
  By CategoriesBy = By.xpath("//*[@text='Categories']");
  /**
   * Mobile Elements
   */
  By ExploreTitleBy = By.xpath("//*[@text='Explore']");

  /**
   * Actions
   */

  public void clickStudio() {
    waitAndClick(StudioBy);
    //Thread.sleep(3000);
    test.get().log(Status.INFO, "Clicked Studio", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickProfile() {
    waitAndClick(ProfileBy);
    test.get().log(Status.INFO, "Clicked Profile", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickHome() {
    waitAndClick(HomeBy);
    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickExplore() {
    waitAndClick(ExploreBy);
    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void clickCategories() {
    waitAndClick(CategoriesBy);
    test.get().log(Status.INFO, "Clicked Home", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void userOnHomePage() {
    test.get().log(Status.INFO, "Home Page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
  }

  public void assertExploreTitleIsExpected() {
    String toolBarTitleStr = wait.until(ExpectedConditions.visibilityOfElementLocated(ExploreTitleBy)).getText();
    //  Thread.sleep(3000);
    test.get().log(Status.INFO, "Verify Explore button", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
    Assert.assertTrue(toolBarTitleStr.contains("Explore"));
  }
}
