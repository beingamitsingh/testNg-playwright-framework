package regression.object_repository;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegistrationPage {

    private final Page page;

    private final Locator lblPageHeading;
    private final Locator frmRegistrationForm;
    private final Locator rdbMale;
    private final Locator rdbFemale;
    private final Locator txtName;
    private final Locator txtEmail;
    private final Locator txtPassword;
    private final Locator ddlDateOfBirth_date;
    private final Locator ddlDateOfBirth_month;
    private final Locator ddlDateOfBirth_year;
    private final Locator txtFirstName;
    private final Locator txtLastName;
    private final Locator txtCompany;
    private final Locator txtAddress1;
    private final Locator txtAddress2;
    private final Locator ddlCountry;
    private final Locator txtState;
    private final Locator txtCity;
    private final Locator txtZipCode;
    private final Locator txtMobileNumber;
    private final Locator btnCreateAccount;
    private final Locator btnContinue;
    private final Locator lblRegistrationSuccessHeading;
    private final Locator lblRegistrationSuccessMessage;

    public RegistrationPage(Page page) {
        this.page = page;
        this.lblPageHeading = page.locator("h2.title.text-center:has-text(\"Enter Account Information\")");
        this.frmRegistrationForm = page.locator("form[action='/signup']");
        this.rdbMale = page.locator("label[for='id_gender1']");
        this.rdbFemale = page.locator("label[for='id_gender2']");
        this.txtName = page.locator("input[data-qa='name']");
        this.txtEmail = page.locator("input[data-qa='email']");
        this.txtPassword = page.locator("#password");
        this.ddlDateOfBirth_date = page.locator("#days");
        this.ddlDateOfBirth_month = page.locator("#months");
        this.ddlDateOfBirth_year = page.locator("#years");
        this.txtFirstName = page.locator("#first_name");
        this.txtLastName = page.locator("#last_name");
        this.txtCompany = page.locator("#company");
        this.txtAddress1 = page.locator("#address1");
        this.txtAddress2 = page.locator("#address2");
        this.ddlCountry = page.locator("#country");
        this.txtState = page.locator("#state");
        this.txtCity = page.locator("#city");
        this.txtZipCode = page.locator("#zipcode");
        this.txtMobileNumber = page.locator("#mobile_number");

        this.btnCreateAccount = page.locator("button[data-qa='create-account']");
        this.lblRegistrationSuccessHeading = page.locator("h2[data-qa='account-created']");
        this.lblRegistrationSuccessMessage = page.getByText("Congratulations! Your new account has been successfully created!");
        this.btnContinue = page.locator("a[data-qa='continue-button']");
    }

    public void selectGenderMale() { rdbMale.click(); }
    public void selectGenderFemale() { rdbFemale.click(); }
    public String getUsername() { return txtName.inputValue(); }
    public String getEmail() { return txtEmail.inputValue(); }
    public void enterPassword(String password) { txtPassword.fill(password); }
    public void setDdlDateOfBirth_date(String dob_date) { ddlDateOfBirth_date.selectOption(dob_date); }
    public void setDdlDateOfBirth_month(String dob_month) { ddlDateOfBirth_month.selectOption(dob_month); }
    public void setDdlDateOfBirth_year(String dob_year) { ddlDateOfBirth_year.selectOption(dob_year); }

    public void enterFirstName(String firstName) { txtFirstName.fill(firstName); }
    public void enterLastName(String lastName) { txtLastName.fill(lastName); }
    public void enterCompany(String company) { txtCompany.fill(company); }
    public void enterAddress1(String address) { txtAddress1.fill(address); }
    public void enterAddress2(String address) { txtAddress2.fill(address); }
    public void setDdlCountry(String country) { ddlCountry.selectOption(country); }
    public void enterState(String state) { txtState.fill(state); }
    public void enterCity(String city) { txtCity.fill(city); }
    public void enterZipCode(String zipCode) { txtZipCode.fill(zipCode); }
    public void enterMobileNumber(String mobileNumber) { txtMobileNumber.fill(mobileNumber); }
    public void clickRegister() { btnCreateAccount.click();}

    public boolean isRegistrationSuccessMessageDisplayed() { return lblRegistrationSuccessHeading.isVisible(); }
    public boolean isPageHeadingDisplayed() { return lblPageHeading.isVisible(); }
    public boolean isRegistrationFormAccessible() { return frmRegistrationForm.isVisible() && frmRegistrationForm.isEnabled(); }
    public boolean isRegistrationContinueButtonAccessible() { return btnContinue.isVisible() && btnContinue.isEnabled(); }

    public String getRegistrationSuccessMessage() { return lblRegistrationSuccessMessage.textContent(); }
}
