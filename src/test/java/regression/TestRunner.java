package regression;

import com.microsoft.playwright.BrowserContext;
import framework.report.TestRunnerListener;
import framework.util.Engine;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestRunnerListener.class)
public class TestRunner extends Engine {

    protected BrowserContext context;

    public TestRunner() {}

    @BeforeSuite
    public void loadConfigAndStartReport() {
        initializeTestSuite();
    }

    @AfterSuite
    public void afterSuite() {
        tearDown();
    }
}