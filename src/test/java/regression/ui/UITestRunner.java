package regression.ui;

import com.microsoft.playwright.BrowserContext;
import framework.report.TestRunnerListener;
import framework.util.Config;
import framework.util.Engine;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestRunnerListener.class)
public class UITestRunner extends Engine {

    protected BrowserContext context;

    @BeforeSuite(alwaysRun = true)
    public void loadConfigAndStartReport() {
        initializeTestSuite();
        browser = setBrowser(Config.getProperty("BROWSER"));
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        tearDown();
    }
}