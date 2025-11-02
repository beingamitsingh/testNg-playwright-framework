package regression.api.book_store;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import regression.api.APITestRunner;

public class BookStoreTest extends APITestRunner {

    @BeforeClass
    public void beforeClass() {
        createAPIContext();
    }

    @AfterClass
    public void afterClass() {
        closeContext();
    }
}
