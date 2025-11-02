package framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import framework.util.Config;
import framework.util.Engine;
import org.testng.*;

import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TestRunnerListener extends Engine implements ITestListener, ISuiteListener {

    private static String reportPath;
    private static ExtentReports extent;
    private static Map<String, ExtentTest> classLevelTests = new ConcurrentHashMap<>();
    private static ThreadLocal<ExtentTest> testLevel;

    @Override
    public void onStart(ISuite suite) {
        new Config();
        reportPath = Config.getProperty("REPORT_FOLDER") + "/Report_"  + System.currentTimeMillis();
        extent = ExtentManager.getInstance(reportPath);
        extent.setSystemInfo("Suite", suite.getName());
        testLevel = new ThreadLocal<>();
    }

    @Override
    public void onStart(ITestContext context) {
        String className = context.getAllTestMethods()[0].getRealClass().getSimpleName();
        ExtentTest classNode = extent.createTest(className);
        classLevelTests.put(className, classNode);
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        ExtentTest parent = classLevelTests.get(className);
        if (parent == null) {
            synchronized (ExtentManager.class) {
                parent = extent.createTest(className);
                classLevelTests.put(className, parent);
            }
        }
        ExtentTest child = parent.createNode(result.getMethod().getMethodName());
        testLevel.set(child);
        child.log(Status.INFO, "Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testLevel.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testLevel.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        String screenshotPath = capture();
        if (screenshotPath != null) {
            testLevel.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
        }

        // Log retry info if applicable
        Object retryAnalyzer = result.getMethod().getRetryAnalyzer(result);
        if (retryAnalyzer instanceof RetryAnalyzer ra) {
            testLevel.get().log(Status.WARNING, "Retrying test. Attempt: " + (ra.retryCount + 1));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testLevel.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable().getMessage());
    }

    private String capture() {
        String screenshotPath = reportPath + "/screenshots/" + UUID.randomUUID() + ".png";
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));
            return screenshotPath;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
