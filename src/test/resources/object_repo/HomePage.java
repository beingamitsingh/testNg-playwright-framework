package object_repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver=driver;
    }

    public static By applicationLogo = By.id("nava");
    public static By topNavigationBar = By.xpath("//*[@id='navbarExample']");

    public static By logInModal = By.id("logInModalLabel");
    public static By signUpModal =By.id("signInModalLabel");
    public static By aboutUsModal =By.id("videoModalLabel");
    public static By contactUsModal =By.id("exampleModalLabel");

    public static By closePopupButton = By.xpath("//button[@class='close']");

}
