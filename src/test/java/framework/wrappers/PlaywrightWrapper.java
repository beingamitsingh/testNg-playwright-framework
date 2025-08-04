package framework.wrappers;

import com.microsoft.playwright.Page;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaywrightWrapper extends TestWrapper {

    private static final Logger logger = Logger.getLogger(PlaywrightWrapper.class.getName());
    private final Page page;

    public PlaywrightWrapper(Page page) {
        this.page = page;
    }

    public void navigateTo(String url) {
        try {
            page.navigate(url);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Navigation failed: " + url, e);
        }
    }

    public void click(String selector) {
        try {
            page.click(selector);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Click failed on: " + selector, e);
        }
    }

    public void type(String selector, String text) {
        try {
            page.fill(selector, text);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, "Typing failed in: " + selector, e);
        }
    }
}