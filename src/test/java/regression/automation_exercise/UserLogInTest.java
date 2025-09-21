package regression.automation_exercise;

import com.microsoft.playwright.options.LoadState;
import framework.util.Config;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import regression.TestRunner;
import regression.pages.HomePage;
import regression.pages.LaunchPage;
import regression.pages.LoginPage;

public class UserLogInTest extends TestRunner {

    private static String userName;
    private static String password;

    @BeforeClass
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(Config.getProperty("WEB_URL"));
        page.waitForLoadState(LoadState.NETWORKIDLE);
        userName = Config.getProperty("WEB_USERNAME");
        password = Config.getProperty("WEB_PASSWORD");
        LaunchPage launchPage = new LaunchPage(page);
        if (launchPage.isConsentButtonAccessible()) {
            launchPage.clickConsentButton();
        }
        launchPage.navigateToLogInPage();
    }

    @AfterClass
    public void afterClass() {
        context.close();
    }

    @Test
    public void wrongUsernameTest() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterLogInUsername("wrongUsername");
        loginPage.enterLogInPassword(password);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getLblErrorMessage(), "Your email or password is incorrect!");
    }

    @Test
    public void wrongPasswordTest() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterLogInUsername(userName);
        loginPage.enterLogInPassword("wrongPassword");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getLblErrorMessage(), "Your email or password is incorrect!");
    }

    @Test(dependsOnMethods = {"wrongPasswordTest", "wrongUsernameTest"})
    public void validateSuccessfulLogIn() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterLogInUsername(userName);
        loginPage.enterLogInPassword(password);
        loginPage.clickLogin();

        HomePage homePage = new HomePage(page);
        Assert.assertTrue(homePage.lblUsernameIsDisplayed());
    }
}
