package framework.report;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import framework.util.Engine;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunnerListener extends Engine implements ITestListener {

    private static final Logger logger = Logger.getLogger(TestRunnerListener.class.getName());

    @BeforeMethod
    public void setUp() {
        test = extent.createTest(getClass().getSimpleName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO, "Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        String screenshotPath = capture();
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        }

        // Log retry info if applicable
        Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
        if (retryAnalyzer instanceof RetryAnalyzer ra) {
            test.log(Status.WARNING, "Retrying test. Attempt: " + (ra.retryCount + 1));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    private String capture() {
        String path = reportPath + UUID.randomUUID() + ".png";
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(new File(path).toPath()));
            return path;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to capture screenshot with Playwright Page", e);
            return null;
        }
    }
}
