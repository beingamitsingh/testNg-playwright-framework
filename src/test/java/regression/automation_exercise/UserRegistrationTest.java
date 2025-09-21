package regression.automation_exercise;

import com.microsoft.playwright.options.LoadState;
import framework.util.Config;
import framework.util.RandomGen;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import regression.TestRunner;
import regression.pages.LaunchPage;
import regression.pages.LoginPage;
import regression.pages.RegistrationPage;

public class UserRegistrationTest extends TestRunner {

    @BeforeClass
    public void setUp(ITestContext iTestContext) {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(Config.getProperty("WEB_URL"));
        page.waitForLoadState(LoadState.NETWORKIDLE);
        LaunchPage launchPage = new LaunchPage(page);
        if (launchPage.isConsentButtonAccessible()) { launchPage.clickConsentButton(); }
        launchPage.navigateToLogInPage();

        String username = RandomGen.generateRandomString(8);
        String email = username + "@mail.com";
        fillSignUpForm(username, email);
        iTestContext.setAttribute("username", username);
        iTestContext.setAttribute("email", email);
    }

    @BeforeMethod
    public void beforeTest(ITestContext iTestContext) {
        String username = (String) iTestContext.getAttribute("username");
        String email = (String) iTestContext.getAttribute("email");
        RegistrationPage registrationPage = new RegistrationPage(page);
        Assert.assertEquals(registrationPage.getUsername(), username);
        Assert.assertEquals(registrationPage.getEmail(), email);
    }

    @Test
    public void successfulUserRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(page);
        Assert.assertTrue(registrationPage.isPageHeadingDisplayed());
        Assert.assertTrue(registrationPage.isRegistrationFormAccessible());

        fillRegistrationForm(registrationPage);
        registrationPage.clickRegister();
        Assert.assertTrue(registrationPage.isRegistrationSuccessMessageDisplayed());
        Assert.assertTrue(registrationPage.isRegistrationContinueButtonAccessible());
        Assert.assertTrue(registrationPage.getRegistrationSuccessMessage().contains("Your new account has been successfully created"));
    }

    @AfterClass
    public void afterClass() {
        context.close();
    }

    private static void fillRegistrationForm(RegistrationPage registrationPage) {
        registrationPage.selectGenderMale();
        registrationPage.enterPassword(RandomGen.generateRandomString(10));
        registrationPage.setDdlDateOfBirth_date("4");
        registrationPage.setDdlDateOfBirth_month("11");
        registrationPage.setDdlDateOfBirth_year("1996");
        registrationPage.enterFirstName("John");
        registrationPage.enterLastName("Doe");
        registrationPage.enterCompany("Company LLC");
        registrationPage.enterAddress1("123 Main St");
        registrationPage.enterAddress2("Apt 4B");
        registrationPage.setDdlCountry("United States");
        registrationPage.enterState("Ohio");
        registrationPage.enterCity("Columbus");
        registrationPage.enterZipCode("43085");
        registrationPage.enterMobileNumber("1234567890");
    }

    private static void fillSignUpForm(String username, String email) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterSignUpUsername(username);
        loginPage.enterSignUpEmail(email);
        loginPage.clickSignup();
    }
}
