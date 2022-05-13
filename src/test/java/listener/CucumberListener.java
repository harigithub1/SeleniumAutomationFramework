package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import utilities.ThreadLocalExtent;

import static utilities.ThreadLocalDriver.*;

public class CucumberListener extends ThreadLocal implements ConcurrentEventListener {
  public static ExtentReports extent = ThreadLocalExtent.createInstance();

  public static ThreadLocal<ExtentTest> ptest = new ThreadLocal<>();
  public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

  public static String takeScreenshotAsBase64() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ((TakesScreenshot) getTLDriver()).getScreenshotAs(OutputType.BASE64);
  }

  public static String takeScreenshotAsBase64Online() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ((TakesScreenshot) getTLDriverOnline()).getScreenshotAs(OutputType.BASE64);
  }

  public static String takeScreenshotAsBase64OnlineLocal() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ((TakesScreenshot) getTLDriverOnlineLocal()).getScreenshotAs(OutputType.BASE64);
  }

  @Override
  public void setEventPublisher(EventPublisher publisher) {
    publisher.registerHandlerFor(TestRunStarted.class, eventHandlerTestRunStarted);
    publisher.registerHandlerFor(TestCaseStarted.class, eventHandlerTestCaseStarted);
    publisher.registerHandlerFor(TestStepStarted.class, eventHandlerTestStepStarted);
    publisher.registerHandlerFor(TestStepFinished.class, eventHandlerTestStepFinished);
    publisher.registerHandlerFor(TestCaseFinished.class, eventHandlerTestCaseFinished);
    publisher.registerHandlerFor(TestRunFinished.class, eventHandlerTestRunFinished);
  }

  public EventHandler<TestRunStarted> eventHandlerTestRunStarted = new EventHandler<TestRunStarted>() {
    public void receive(TestRunStarted event) {
      System.out.println("Cucumber Event TestRunStarted");
    }
  };
  public EventHandler<TestCaseStarted> eventHandlerTestCaseStarted = new EventHandler<TestCaseStarted>() {
    public void receive(TestCaseStarted event) {
      String testScenarioName = event.getTestCase().getName();
      if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Mobile")) {
        if (String.valueOf(getTLDriver().getCapabilities().getCapability("deviceName")).toLowerCase().contains("iphone")) {
          String deviceName = String.valueOf(getTLDriver().getCapabilities().getCapability("deviceName"));
          String os_version = String.valueOf(getTLDriver().getCapabilities().getCapability("os_version"));
          ExtentTest extentTest = extent.createTest(deviceName + " v" + os_version + ": " + testScenarioName);
          ptest.set(extentTest);
        } else {
          if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Cloud").contains("true")) {
            String deviceName = String.valueOf(getTLDriver().getCapabilities().getCapability("device"));
            String os_version = String.valueOf(getTLDriver().getCapabilities().getCapability("platformVersion"));
            ExtentTest extentTest = extent.createTest(deviceName + " v" + os_version + ": " + testScenarioName);
            ptest.set(extentTest);
          } else {
            String deviceName = String.valueOf(getTLDriver().getCapabilities().getCapability("deviceModel"));
            String os_version = String.valueOf(getTLDriver().getCapabilities().getCapability("platformVersion"));
            ExtentTest extentTest = extent.createTest(deviceName + " v" + os_version + ": " + testScenarioName);
            ptest.set(extentTest);
          }
        }
      } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Online")) {
        String browserName = String.valueOf(getTLDriverOnline().getCapabilities().getCapability("browserName"));
        String platform = String.valueOf(getTLDriverOnline().getCapabilities().getCapability("platform"));
        ExtentTest extentTest = extent.createTest(platform + " " + browserName + ": " + testScenarioName);
        ptest.set(extentTest);
      } else {
        String browserName = getTLDriverOnlineLocal().getClass().getSimpleName().substring(0, 6);
        ExtentTest extentTest = extent.createTest(browserName + ": " + testScenarioName);
        ptest.set(extentTest);
      }
    }
  };
  private EventHandler<TestStepStarted> eventHandlerTestStepStarted = new EventHandler<TestStepStarted>() {
    public void receive(TestStepStarted event) {
      if (event.getTestStep() instanceof PickleStepTestStep) {
        String testStepName = ((PickleStepTestStep) event.getTestStep()).getStep().getText();
        ExtentTest extentTest = ptest.get().createNode(testStepName);
        test.set(extentTest);
      }
    }
  };
  private EventHandler<TestStepFinished> eventHandlerTestStepFinished = new EventHandler<TestStepFinished>() {
    public void receive(TestStepFinished event) {
      if (event.getTestStep() instanceof PickleStepTestStep) {
        if (event.getResult().getStatus().toString().equalsIgnoreCase("passed")) {
          if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Online")) {
            test.get().log(Status.INFO, "Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Online()).build());
          } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("LocalBrowser")) {
            test.get().log(Status.INFO, "Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64OnlineLocal()).build());
          } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Mobile")) {
            test.get().log(Status.INFO, "Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
          }
          test.get().pass("Test passed");
        } else if (event.getResult().getStatus().toString().equalsIgnoreCase("failed")) {
          if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Online")) {
            test.get().log(Status.INFO, "Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Online()).build());
          } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("LocalBrowser")) {
            test.get().log(Status.INFO, "Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64OnlineLocal()).build());
          } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("OnlineOrMobile").contains("Mobile")) {
            test.get().log(Status.INFO, "Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64()).build());
          }
          test.get().fail("Test failed: " + event.getResult().getError());
        } else {
          test.get().skip("Test skipped");
        }
      }
    }
  };
  public EventHandler<TestCaseFinished> eventHandlerTestCaseFinished = new EventHandler<TestCaseFinished>() {
    public void receive(TestCaseFinished event) {
      extent.flush();
/*    Below code is to update test status in jira
      String testScenarioKey = event.getTestCase().getTags().get(1).substring(9);
      String issueIDOfScenario = "get issue id using above scenario key and GetScenarioIssueIDsUsingKeyFromJiraAPI.java class";
      int testResult;
      if(event.getResult().getStatus().toString().equalsIgnoreCase("PASSED")){
        testResult=1;
        String executionID = "get execution id from AddZephyrTestsToTestCycleAndGetExecutionID.java class" +
                "by providing issueIDOfScenario and cycleID";
        Now execute the test by calling ExecuteTestsinTestCycleInZephyrSquadCloud.java class
      }
      if(event.getResult().getStatus().toString().equalsIgnoreCase("FAILED")){
        testResult=2;
        String executionID = "get execution id from AddZephyrTestsToTestCycleAndGetExecutionID.java class" +
                "by providing issueIDOfScenario and cycleID";
        Now execute the test by calling ExecuteTestsinTestCycleInZephyrSquadCloud.java class
      }
*/
    }
  };
  private EventHandler<TestRunFinished> eventHandlerTestRunFinished = new EventHandler<TestRunFinished>() {
    public void receive(TestRunFinished event) {
    }
  };
}
/*
https://www.javadoc.io/doc/io.cucumber/cucumber-core/4.7.2/cucumber/api/event/Event.html
*/
