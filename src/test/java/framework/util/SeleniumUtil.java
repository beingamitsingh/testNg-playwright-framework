package framework.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtil {

    public static WebDriver setBrowser(String browser)  {
        WebDriver webDr = null;
        switch (browser)  {
            case "Chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-popup-blocking");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions. addArguments("--disable-extensions");
                chromeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                webDr = new ChromeDriver(chromeOptions);
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                webDr = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser specified in Config file: " + browser);
                break;
        }
        return webDr;
    }

    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement webElement, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static Alert waitUntilAlertIsPresent(WebDriver driver, By elementLocator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
}
