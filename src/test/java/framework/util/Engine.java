package framework.util;

import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.*;

import java.util.Map;

public class Engine {

    public static Page page;
    private static Playwright playwright;
    protected static Browser browser;
    protected static ExtentReports extent;

    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();
    private static final ThreadLocal<APIRequestContext> apiContextThread = new ThreadLocal<>();

    public void initializeTestSuite() {
        new Config();
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

    protected Browser setBrowser(String browser)  {
        playwright = Playwright.create();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setSlowMo(50);
        return switch (browser.toLowerCase()) {
            case "chrome" -> playwright.chromium().launch(options.setChannel(browser));
            case "firefox" -> playwright.firefox().launch();
            case "safari" -> playwright.webkit().launch();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    public static void createBrowserContext() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        contextThread.set(context);
        pageThread.set(page);
    }

    public void createAPIContext() {
        APIRequest request = playwright.request();
        APIRequest.NewContextOptions options = new APIRequest.NewContextOptions()
            .setBaseURL("https://jsonplaceholder.typicode.com").setExtraHTTPHeaders(Map.of(
                "Content-Type", "application/json",
                "Accept", "application/json"
            ));
        APIRequestContext context = request.newContext(options);
        apiContextThread.set(context);
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static BrowserContext getContext() {
        return contextThread.get();
    }

    public static void closeContext() {
        if (getContext() != null) {
            getContext().close();
            contextThread.remove();
        }
        if (pageThread.get() != null) {
            pageThread.remove();
        }
        if (apiContextThread.get() != null) {
            apiContextThread.remove();
        }
    }
}
