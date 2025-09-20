package framework.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import framework.report.ExtentManager;

public class Engine {

    public static Page page;
    public static ExtentTest test;
    private static final Playwright playwright = Playwright.create();
    private Browser browser;
    private BrowserContext context;
    protected static ExtentReports extent;
    protected static String reportPath;

    public void initializeTestSuite() {
        new Config();
        browser = setBrowser(Config.getProperty("BROWSER"));
        context = browser.newContext();
        page = context.newPage();

        reportPath = Config.getProperty("REPORT_PATH") + "/Report_"  + System.currentTimeMillis();
        extent = ExtentManager.getInstance(reportPath);
    }

    public void tearDown() {
        context.close();
        browser.close();
        if (playwright != null) playwright.close();
        extent.flush();
    }

    private Browser setBrowser(String browser)  {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setSlowMo(50);
        return switch (browser.toLowerCase()) {
            case "chrome" -> playwright.chromium().launch(options.setChannel(browser));
            case "firefox" -> playwright.firefox().launch();
            case "safari" -> playwright.webkit().launch();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
