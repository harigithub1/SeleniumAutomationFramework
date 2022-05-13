package cucumber.tests;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.DesiredCapabilitiesUtil;
import utilities.ThreadLocalDriver;

import java.io.IOException;
import java.net.URL;

/**
 * This class uses multithreading to run tests parallel
 */
@CucumberOptions(
        monochrome = true,
        tags = "@MyntraScenario",
        features = "src/test/java/cucumber/features",
        glue = "cucumber.stepdefinitions",
        publish = false,
        plugin = {"listener.CucumberListener", "pretty",
                "html:target/cucumber-reports/CucumberReport2.html",
                "json:target/cucumber-reports/cucumber-report2.json"}
)
public class TestNGParallelRunner1Online {

  private TestNGCucumberRunner testNGCucumberRunner;
  private final DesiredCapabilitiesUtil desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();

  @BeforeClass(alwaysRun = true)
  public void setUpClass() {
    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
  }

  @BeforeMethod
  @Parameters({"platform", "platformVersion", "browser"})
  public void setup(String platform, String platformVersion, String browser) throws IOException {
    ConfigReader configReader = new ConfigReader();
    String browserStackUsername = configReader.config().getProperty("BrowserStackUsername");
    String browserStackAccessKey = configReader.config().getProperty("BrowserStackAccessKey");
    String browserStackServer = configReader.config().getProperty("BrowserStackServer");
    DesiredCapabilities caps = desiredCapabilitiesUtil.getDesiredCapabilitiesOnline(platform, platformVersion, browser);
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Cloud").equalsIgnoreCase("true")) {
      ThreadLocalDriver.setTLDriverOnline(new RemoteWebDriver(new URL("http://" + browserStackUsername + ":" + browserStackAccessKey + "@" + browserStackServer + "/wd/hub"), caps));
    } else {
      System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\chromedriver_win32\\chromedriver.exe");
      ThreadLocalDriver.setTLDriverOnlineLocal(new ChromeDriver());
//      ThreadLocalDriver.setTLDriverOnlineLocal(new ChromeDriver(caps));
    }
  }

  @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
  public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
    testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
  }

  /**
   * Returns two dimensional array of PickleEventWrapper scenarios
   * with their associated CucumberFeatureWrapper feature.
   *
   * @return a two dimensional array of scenarios features.
   */
  @DataProvider
  public Object[][] scenarios() {
    return testNGCucumberRunner.provideScenarios();
  }

  @AfterMethod
  public synchronized void teardown() {
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Online"))
      ThreadLocalDriver.getTLDriverOnline().quit();
    else
      ThreadLocalDriver.getTLDriverOnlineLocal().quit();
  }

  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    testNGCucumberRunner.finish();
  }
}