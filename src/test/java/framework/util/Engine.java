package framework.util;

import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.*;

public class Engine {

    public static Page page;
    private static Playwright playwright;
    protected static Browser browser;
    protected static ExtentReports extent;

    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();

    public void initializeTestSuite() {
        new Config();
        browser = setBrowser(Config.getProperty("BROWSER"));
    }

    public void tearDown() {
        if (browser != null) {
            browser.close();
            playwright.close();
            browser = null;
            playwright = null;
        }
        if (extent != null) extent.flush();
    }

    private Browser setBrowser(String browser)  {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setSlowMo(50);
        return switch (browser.toLowerCase()) {
            case "chrome" -> playwright.chromium().launch(options.setChannel(browser));
            case "firefox" -> playwright.firefox().launch();
            case "safari" -> playwright.webkit().launch();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    public static void createContextForClass() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        contextThread.set(context);
        pageThread.set(page);
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static BrowserContext getContext() {
        return contextThread.get();
    }

    public static void closeContext() {
        if (contextThread.get() != null) {
            contextThread.get().close();
            contextThread.remove();
        }
        if (pageThread.get() != null) {
            pageThread.remove();
        }
    }
}
