package ui_regression;

import framework.report.TestRunnerListener;
import framework.wrappers.TestWrapper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestRunnerListener.class)
public class TestRunner extends TestWrapper {

    @BeforeSuite
    public void loadConfigAndStartReport() {
        TestWrapper.start();
    }

    @BeforeMethod
    public void beforeMethod() {
        test = extent.createTest(getClass().getSimpleName());
    }

    @AfterSuite
    public void afterSuite() {
        TestWrapper.tearDown();
    }
}