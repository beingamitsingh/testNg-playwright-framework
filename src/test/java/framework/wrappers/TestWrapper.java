package framework.wrappers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import framework.Config;
import framework.report.ExtentManager;
import framework.util.PlaywrightUtil;
import framework.util.SeleniumUtil;
import org.openqa.selenium.WebDriver;

public class TestWrapper {

    protected static String framework;
    protected static WebDriver driver;
    protected static Playwright playwright;
    protected static Page page;
    protected static ExtentReports extent;
    public static ExtentTest test;

    public static void start() {
        new Config();
        String browser = Config.getProperty("BROWSER");
        framework = Config.getProperty("testFramework").toLowerCase();
        extent = ExtentManager.getInstance();
        switch (framework) {
            case "selenium":
                driver = SeleniumUtil.setBrowser(browser);
                break;
            case "playwright":
                page = PlaywrightUtil.setPage(browser);
                break;
            default:
                throw new RuntimeException("Unsupported framework: " + framework);
        }
    }

    public static void tearDown() {
        switch (framework) {
            case "selenium":
                if (driver != null) driver.quit();
                break;
            case "playwright":
                if (page != null) page.close();
                if (playwright != null) playwright.close();
                break;
        }
        extent.flush();
    }
}
