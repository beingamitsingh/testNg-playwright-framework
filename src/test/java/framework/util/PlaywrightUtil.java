package framework.util;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightUtil {

    public static Page page;
    private static final Playwright playwright = Playwright.create();

    public static Page setPage(String browser)  {
        return switch (browser.toLowerCase()) {
            case "chrome" -> playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setChannel("chrome")).newPage();
            case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions()).newPage();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
