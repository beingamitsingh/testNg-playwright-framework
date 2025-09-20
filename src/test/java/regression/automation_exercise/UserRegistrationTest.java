package regression.automation_exercise;

import com.microsoft.playwright.options.LoadState;
import framework.util.Config;
import framework.util.RandomGen;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import regression.TestRunner;
import regression.pages.RegistrationPage;

public class UserRegistrationTest extends TestRunner {

    @BeforeClass
    public void setUp() {
        page.navigate(Config.getProperty("WEB_URL") + "/register.htm");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    @Test
    public void successfulUserRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(page);
        fillRegistrationForm(registrationPage);
        registrationPage.clickRegister();

        Assert.assertTrue(registrationPage.isRegistrationSuccessMessageDisplayed());
        Assert.assertEquals(registrationPage.getRegistrationSuccessMessage(), "The username and password could not be verified.");
    }

    private static void fillRegistrationForm(RegistrationPage registrationPage) {
        String firstName, lastName;
        firstName = "Hendrik";
        lastName = RandomGen.generateRandomString(5);

        registrationPage.enterFirstName("Hendrik");
        registrationPage.enterLastName("Testinen");
        registrationPage.enterAddress("Lootuse Tanav 13");
        registrationPage.enterCity("Tallinn");
        registrationPage.enterState("Harjumaa");
        registrationPage.enterZipCode("10102");
        registrationPage.enterPhone("5557890");
        registrationPage.enterSSN("123-405-6789");
        registrationPage.enterUsername(firstName + "." + lastName);

        test.info("Generated username: " + registrationPage.getUsername());
        registrationPage.enterPassword("password123");
        registrationPage.enterConfirmedPassword("password123");
    }
}
