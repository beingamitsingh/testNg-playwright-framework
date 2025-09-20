package regression.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegistrationPage {

    private final Page page;

    private final Locator txtFirstName;
    private final Locator txtLastName;
    private final Locator txtAddress;
    private final Locator txtcity;
    private final Locator txtState;
    private final Locator txtZipCode;
    private final Locator txtPhone;
    private final Locator txtSSN;
    private final Locator txtUsername;
    private final Locator txtPassword;
    private final Locator txtConfirm;
    private final Locator btnRegister;

    private final Locator btnCaptchaAccept;
    private final Locator lblRegistrationSuccessMessage;

    public RegistrationPage(Page page) {
        this.page = page;
        this.txtFirstName = page.locator("#customer\\.firstName");
        this.txtLastName = page.locator("#customer\\.lastName");
        this.txtAddress = page.locator("#customer\\.address\\.street");
        this.txtcity = page.locator("#customer\\.address\\.city");
        this.txtState = page.locator("#customer\\.address\\.state");
        this.txtZipCode = page.locator("#customer\\.address\\.zipCode");
        this.txtPhone = page.locator("#customer\\.phoneNumber");
        this.txtSSN = page.locator("#customer\\.ssn");
        this.txtUsername = page.locator("#customer\\.username");
        this.txtPassword = page.locator("#customer\\.password");
        this.txtConfirm = page.locator("#repeatedPassword");
        this.btnRegister = page.locator("input[value='Register']");
        this.btnCaptchaAccept = page.locator("text=Verify you are human");
        this.lblRegistrationSuccessMessage = page.locator("text=Your account was created successfully. You are now logged in.");
    }

    public String getUsername() { return txtUsername.inputValue(); }

    public void enterFirstName(String firstName) { txtFirstName.fill(firstName); }
    public void enterLastName(String lastName) { txtLastName.fill(lastName); }
    public void enterAddress(String address) { txtAddress.fill(address); }
    public void enterCity(String city) { txtcity.fill(city); }
    public void enterState(String state) { txtState.fill(state); }
    public void enterZipCode(String zipCode) { txtZipCode.fill(zipCode); }
    public void enterPhone(String phone) { txtPhone.fill(phone); }
    public void enterSSN(String ssn) { txtSSN.fill(ssn); }
    public void enterUsername(String username) { txtUsername.fill(username); }
    public void enterPassword(String password) { txtPassword.fill(password); }
    public void enterConfirmedPassword(String confirmPassword) { txtConfirm.fill(confirmPassword); }
    public void clickRegister() { btnRegister.click();}
    public boolean isRegistrationSuccessMessageDisplayed() { return lblRegistrationSuccessMessage.isVisible(); }
    public String getRegistrationSuccessMessage() { return lblRegistrationSuccessMessage.textContent(); }
}
