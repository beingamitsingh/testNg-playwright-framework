package regression;

import framework.report.TestRunnerListener;
import framework.util.Engine;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestRunnerListener.class)
public class TestRunner extends Engine {

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