package regression.object_repository;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    private final Locator lblUsername;

    public HomePage(Page page) {
        this.page = page;
        this.lblUsername = page.locator("li a:has-text('Logged in as')");
    }

    public boolean lblUsernameIsDisplayed() { return lblUsername.isVisible(); }
}
