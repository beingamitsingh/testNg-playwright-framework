package ui_regression.playwright_automation;

import framework.Config;
import framework.wrappers.PlaywrightWrapper;
import org.testng.annotations.Test;
import ui_regression.TestRunner;

public class SampleTest extends TestRunner {

    PlaywrightWrapper pw = new PlaywrightWrapper(page);
    final String testUrl = Config.getProperty("SAMPLE_URL_1");

    @Test
    public void sampleTest() {
        test.info("This is sample test");
    }

    @Test
    public void hybridTest() {
        pw.navigateTo(testUrl);
        pw.click("a[href='/forgot_password']");
    }
}
