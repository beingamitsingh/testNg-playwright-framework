package regression.api;

import framework.util.Engine;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class APITestRunner extends Engine {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        initializeTestSuite();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        tearDown();
    }
}
