import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = false;
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void successfulFullFillFormTest() {
        open("/automation-practice-form");

        // ---- Личные данные ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userEmail]").setValue("og@potter.com");
        $("[id=userNumber]").setValue("8800555353");

        // ---- Пол ----
        $("label[for='gender-radio-1']").click(); // Мужской

        // ---- Дата рождения ----
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();

        // ---- Предметы ----
        $("[id=subjectsInput]").setValue("Math");
        $(".subjects-auto-complete__option").click();

        // ---- Хобби ----
        $("label[for='hobbies-checkbox-1']").click(); // Спорт
        $("label[for='hobbies-checkbox-2']").click(); // Чтение
        $("label[for='hobbies-checkbox-3']").click(); // Музыка

        // ---- Загрузка фото ----
        $("[id=uploadPicture]").uploadFile(new File("src/test/resources/CatHarry.jpg"));

        // ---- Текущий адрес ----
        $("[id=currentAddress]").setValue("221B Baker Street");

        // ---- Штат и город ----
        $("[id=state]").click();
        $$("[id^=react-select-3-option]").findBy(text("NCR")).click();

        $("[id=city]").click();
        $$("[id^=react-select-4-option]").findBy(text("Delhi")).click();

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка модального окна ----
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

        // ---- Личные данные ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userNumber]").setValue("8005553535");

        // ---- Пол ----
        $("label[for='gender-radio-2']").click(); // Женский

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка модального окна ----
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

    // ======================== НЕГАТИВНЫЕ ТЕСТЫ ========================

    @Test
    void invalidSubmitWithEmptyFormTest() {
        open("/automation-practice-form");

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: модальное окно не появилось, обязательные поля подсвечены красным ----
        $(".modal-body").shouldNot(exist);
        $("[id=firstName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=lastName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void invalidSubmitWithoutFirstNameTest() {
        open("/automation-practice-form");

        // ---- Заполнение всех обязательных полей, кроме имени ----
        $("[id=lastName]").setValue("Potter");
        $("[id=userNumber]").setValue("8800555353");
        $("label[for='gender-radio-1']").click();

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: модальное окно не появилось, поле имени подсвечено красным ----
        $(".modal-body").shouldNot(exist);
        $("[id=firstName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void invalidSubmitWithoutLastNameTest() {
        open("/automation-practice-form");

        // ---- Заполнение всех обязательных полей, кроме фамилии ----
        $("[id=firstName]").setValue("Harry");
        $("[id=userNumber]").setValue("8800555353");
        $("label[for='gender-radio-1']").click();

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: модальное окно не появилось, поле фамилии подсвечено красным ----
        $(".modal-body").shouldNot(exist);
        $("[id=lastName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void invalidSubmitWithoutGenderTest() {
        open("/automation-practice-form");

        // ---- Заполнение всех обязательных полей, кроме пола ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userNumber]").setValue("8800555353");

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: модальное окно не появилось, лейблы гендера подсвечены красным ----
        $(".modal-body").shouldNot(exist);
        $("label[for='gender-radio-1']").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("label[for='gender-radio-2']").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("label[for='gender-radio-3']").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
    }

    @Test
    void invalidSubmitWithInvalidEmailTest() {
        open("/automation-practice-form");

        // ---- Заполнение обязательных полей с невалидным email ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userEmail]").setValue("not-an-email");
        $("[id=userNumber]").setValue("8800555353");
        $("label[for='gender-radio-1']").click();

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: модальное окно не появилось, поле email подсвечено красным ----
        $(".modal-body").shouldNot(exist);
        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }


}
