import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class RegistrationTests {

    @BeforeAll
    static void beforeAll() {
//        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = false;
    }



}
