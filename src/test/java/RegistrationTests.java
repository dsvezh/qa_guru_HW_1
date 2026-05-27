import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successfulFullFillFormTest() {
        open("/automation-practice-form");

        // ---- Personal info ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userEmail]").setValue("og@potter.com");
        $("[id=userNumber]").setValue("8800555353");

        // ---- Gender ----
        $("label[for='gender-radio-1']").click(); // Male

        // ---- Date of Birth ----
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();

        // ---- Subjects ----
        $("[id=subjectsInput]").setValue("Math");
        $(".subjects-auto-complete__option").click();

        // ---- Hobbies ----
        $("label[for='hobbies-checkbox-1']").click(); // Sports
        $("label[for='hobbies-checkbox-2']").click(); // Reading
        $("label[for='hobbies-checkbox-3']").click(); // Music

        // ---- Upload Picture ----
        $("[id=uploadPicture]").uploadFile(new File("src/test/resources/CatHarry.jpg"));

        // ---- Current Address ----
        $("[id=currentAddress]").setValue("221B Baker Street");

        // ---- State & City ----
        $("[id=state]").click();
        $$("[id^=react-select-3-option]").findBy(text("NCR")).click();

        $("[id=city]").click();
        $$("[id^=react-select-4-option]").findBy(text("Delhi")).click();

        // ---- Submit ----
        $("[id=submit]").click();

        // ---- Verify result modal ----
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        $$(".table-responsive tbody tr").findBy(text("Student Name"))
                .shouldHave(text("Harry Potter"));
        $$(".table-responsive tbody tr").findBy(text("Student Email"))
                .shouldHave(text("og@potter.com"));
        $$(".table-responsive tbody tr").findBy(text("Gender"))
                .shouldHave(text("Male"));
        $$(".table-responsive tbody tr").findBy(text("Mobile"))
                .shouldHave(text("8800555353"));
        $$(".table-responsive tbody tr").findBy(text("Date of Birth"))
                .shouldHave(text("01 January,2000"));
        $$(".table-responsive tbody tr").findBy(text("Subjects"))
                .shouldHave(text("Maths"));
        $$(".table-responsive tbody tr").findBy(text("Hobbies"))
                .shouldHave(text("Sports, Reading, Music"));
        $$(".table-responsive tbody tr").findBy(text("Picture"))
                .shouldHave(text("CatHarry.jpg"));
        $$(".table-responsive tbody tr").findBy(text("Address"))
                .shouldHave(text("221B Baker Street"));
        $$(".table-responsive tbody tr").findBy(text("State and City"))
                .shouldHave(text("NCR Delhi"));
    }

    @Test
    void successfulRequiredFillFormTest() {
        open("/automation-practice-form");

        // ---- Personal info ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userNumber]").setValue("8005553535");

        // ---- Gender ----
        $("label[for='gender-radio-2']").click(); // Female

        // ---- Submit ----
        $("[id=submit]").click();

        // ---- Verify result modal ----
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $$(".table-responsive tbody tr").findBy(text("Student Name"))
                .shouldHave(text("Harry Potter"));
        $$(".table-responsive tbody tr").findBy(text("Gender"))
                .shouldHave(text("Female"));
        $$(".table-responsive tbody tr").findBy(text("Mobile"))
                .shouldHave(text("8005553535"));
        $$(".table-responsive tbody tr").findBy(text("Date of Birth"))
                .shouldHave(text(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH))));

    }

}
