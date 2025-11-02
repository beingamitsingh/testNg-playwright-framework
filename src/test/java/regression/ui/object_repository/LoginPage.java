package regression.ui.object_repository;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class LoginPage {

    private final Page page;

    private final Locator txtLogInEmail;
    private final Locator txtLogInPassword;
    private final Locator btnLogIn;
    private final Locator lblErrorMessage;
    private final Locator txtSignUpUsername;
    private final Locator txtSignUpEmail;
    private final Locator btnSignUp;

    public LoginPage(Page page) {
        this.page = page;
        this.txtLogInEmail = page.locator("input[data-qa='login-email']");
        this.txtLogInPassword = page.locator("input[data-qa='login-password']");
        this.btnLogIn = page.locator("button[data-qa='login-button']");
        this.lblErrorMessage = page.locator("p:has-text('Your email or password is incorrect!')");

        this.txtSignUpUsername = page.locator("input[data-qa='signup-name']");
        this.txtSignUpEmail = page.locator("input[data-qa='signup-email']");
        this.btnSignUp = page.locator("button[data-qa='signup-button']");
    }

    public void enterLogInUsername(String username) {
        txtLogInEmail.fill(username);
    }

    public void enterLogInPassword(String password) {
        txtLogInPassword.fill(password);
    }

    public void clickLogin() { btnLogIn.click(); }

    public boolean isErrorMessageDisplayed() {
        return lblErrorMessage.isVisible();
    }

    public String getLblErrorMessage() {
        return lblErrorMessage.textContent();
    }

    public void enterSignUpUsername(String username) {
        txtSignUpUsername.fill(username);
    }

    public void enterSignUpEmail(String email) {
        txtSignUpEmail.fill(email);
    }

    public void clickSignup() {
        btnSignUp.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}
