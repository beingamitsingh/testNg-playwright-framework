package regression.object_repository;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class LaunchPage {

    private final Page page;
    private final Locator btnLogInAndSignUp;
    private final Locator btnProducts;
    private final Locator btnContactUs;
    private final Locator imgLogo;
    private final Locator btnConsent;

    public LaunchPage(Page page) {
        this.page = page;
        this.btnLogInAndSignUp = page.locator("a[href='/login']");
        this.btnProducts = page.locator("a[href='/products']");
        this.btnContactUs = page.locator("a[href='/contact_us']");
        this.imgLogo = page.locator("img[alt='Website for automation practice']");
        this.btnConsent = page.locator("button:has-text('Consent')");
    }

    public void navigateToLogInPage() {
        btnLogInAndSignUp.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void navigateToProductsPage() {
        btnProducts.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void navigateToContactUsPage() {
        btnContactUs.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public void clickConsentButton() {
        btnConsent.click();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public boolean isConsentButtonAccessible() { return btnConsent.isVisible() && btnConsent.isEnabled(); }

}
