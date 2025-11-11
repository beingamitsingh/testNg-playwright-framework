package regression.ui.user_management;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import framework.util.Config;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import regression.ui.UITestRunner;
import regression.ui.object_repository.HomePage;
import regression.ui.object_repository.LaunchPage;
import regression.ui.object_repository.LoginPage;

public class UserLogInTest extends UITestRunner {

    private static String userName;
    private static String password;

    @BeforeClass
    public void beforeClass() {
        createBrowserContext();
        page = getPage();

        page.navigate(Config.getProperty("WEB_URL"), new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
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
        closeContext();
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
