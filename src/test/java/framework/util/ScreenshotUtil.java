package framework.util;

import com.microsoft.playwright.Page;
import framework.wrappers.TestWrapper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotUtil extends TestWrapper {

    private static final Logger logger = Logger.getLogger(ScreenshotUtil.class.getName());

    public static String capture(WebDriver driver) {
        String path = "test-output/screenshots/" + UUID.randomUUID() + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File dest = new File(path);
            if (!dest.getParentFile().exists() && !dest.getParentFile().mkdirs()) {
                logger.warning("Failed to create directories for screenshot path: " + dest.getParent());
            }
            Files.copy(src.toPath(), dest.toPath());
            return path;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to capture screenshot with WebDriver", e);
            return null;
        }
    }

    public static String capture() {
        String path = "test-output/screenshots/" + UUID.randomUUID() + ".png";
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(new File(path).toPath()));
            return path;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to capture screenshot with Playwright Page", e);
            return null;
        }
    }
}
