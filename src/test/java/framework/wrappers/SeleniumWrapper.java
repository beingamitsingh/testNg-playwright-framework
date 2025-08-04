package framework.wrappers;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleniumWrapper extends TestWrapper {

    private static final Logger logger = Logger.getLogger(PlaywrightWrapper.class.getName());
    private final WebDriver driver;

    public SeleniumWrapper(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url) {
        try {
            driver.get(url);
            test.log(Status.INFO, "Navigated to: " + url);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Navigation failed: " + url, e);
        }
    }

    public void click(By locator) {
        try {
            driver.findElement(locator).click();
            test.log(Status.INFO, "Clicked element: " + locator.toString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Click failed on: " + locator.toString(), e);
        }
    }

    public void type(By locator, String text) {
        try {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            test.log(Status.INFO, "Typed '" + text + "' in: " + locator.toString());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Typing failed in: " + locator.toString(), e);
        }
    }
}