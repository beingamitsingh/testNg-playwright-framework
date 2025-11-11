package regression.api.book_store;

import com.microsoft.playwright.APIRequestContext;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import regression.api.APITestRunner;

public class BookStoreTest extends APITestRunner {

    private static APIRequestContext context;

    @BeforeClass
    public void beforeClass() {
        createAPIContext();
    }

    @AfterClass
    public void afterClass() {
        closeContext();
    }

    @Test
    public void getAllBooksFromStore(ITestContext context) {
        getAPIContext().get("/BookStore/v1/Books");
    }
}
