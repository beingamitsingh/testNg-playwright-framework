package framework.report;

import com.aventstack.extentreports.Status;
import framework.util.ScreenshotUtil;
import framework.wrappers.TestWrapper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

public class TestRunnerListener extends TestWrapper implements ITestListener {

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

        String screenshotPath = framework.equals("selenium")
                ? ScreenshotUtil.capture(driver)
                : ScreenshotUtil.capture();

        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath, "Success Screenshot");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Log retry info if applicable
        Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
        if (retryAnalyzer instanceof RetryAnalyzer ra) {
            test.log(Status.WARNING, "Retrying test. Attempt: " + (ra.retryCount + 1));
        }

        String screenshotPath = framework.equals("selenium")
                ? ScreenshotUtil.capture(driver)
                : ScreenshotUtil.capture();

        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
