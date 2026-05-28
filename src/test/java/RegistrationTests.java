import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase {

    @Test
    void successfulFullFillFormTest() {
        open("/automation-practice-form");

        // ---- Личные данные ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userEmail]").setValue("og@potter.com");
        $("[id=userNumber]").setValue("8800555353");

        // ---- Пол ----
        $("#genterWrapper").$$("label").findBy(text("Male")).click(); // Мужской

        // ---- Дата рождения ----
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();

        // ---- Предметы ----
        $("[id=subjectsInput]").setValue("Math");
        $(".subjects-auto-complete__option").click();

        // ---- Хобби ----
        $("#hobbiesWrapper").$$("label").findBy(text("Sports")).click(); // Спорт
        $("#hobbiesWrapper").$$("label").findBy(text("Reading")).click(); // Чтение
        $("#hobbiesWrapper").$$("label").findBy(text("Music")).click(); // Музыка

        // ---- Загрузка фото ----
        $("[id=uploadPicture]").uploadFromClasspath("CatHarry.jpg");

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

        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text("Harry Potter"));
        $(".table-responsive").$(byText("Student Email")).parent()
                .shouldHave(text("og@potter.com"));
        $(".table-responsive").$(byText("Gender")).parent()
                .shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent()
                .shouldHave(text("8800555353"));
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text("01 January,2000"));
        $(".table-responsive").$(byText("Subjects")).parent()
                .shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent()
                .shouldHave(text("Sports, Reading, Music"));
        $(".table-responsive").$(byText("Picture")).parent()
                .shouldHave(text("CatHarry.jpg"));
        $(".table-responsive").$(byText("Address")).parent()
                .shouldHave(text("221B Baker Street"));
        $(".table-responsive").$(byText("State and City")).parent()
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
        $("#genterWrapper").$$("label").findBy(text("Female")).click(); // Женский

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка модального окна ----
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text("Harry Potter"));
        $(".table-responsive").$(byText("Gender")).parent()
                .shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent()
                .shouldHave(text("8005553535"));
        $(".table-responsive").$(byText("Date of Birth")).parent()
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
        $("#genterWrapper").$$("label").findBy(text("Male")).click();

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
        $("#genterWrapper").$$("label").findBy(text("Male")).click();

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
        $("#genterWrapper").$$("label").findBy(text("Male"))
                .shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("#genterWrapper").$$("label").findBy(text("Female"))
                .shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        $("#genterWrapper").$$("label").findBy(text("Other"))
                .shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
    }

    @Test
    void invalidSubmitWithInvalidEmailTest() {
        open("/automation-practice-form");

        // ---- Заполнение обязательных полей с невалидным email ----
        $("[id=firstName]").setValue("Harry");
        $("[id=lastName]").setValue("Potter");
        $("[id=userEmail]").setValue("not-an-email");
        $("[id=userNumber]").setValue("8800555353");
        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: модальное окно не появилось, поле email подсвечено красным ----
        $(".modal-body").shouldNot(exist);
        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }


}
