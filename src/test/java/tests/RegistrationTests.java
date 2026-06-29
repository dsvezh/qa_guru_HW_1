package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class RegistrationTests extends TestBase {

    @Test
    void successfulFullFillFormTest() {
        open(PRACTICE_FORM_URL);

        $("[id=firstName]").setValue(REG_FIRST_NAME);
        $("[id=lastName]").setValue(REG_LAST_NAME);
        $("[id=userEmail]").setValue(REG_EMAIL);
        $("[id=userNumber]").setValue(REG_MOBILE);

        $("#genterWrapper").$$("label").findBy(text(REG_MALE_GENDER)).click();

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(REG_BIRTH_MONTH);
        $(".react-datepicker__year-select").selectOption(REG_BIRTH_YEAR);
        $(".react-datepicker__day--001:not(.react-datepicker__day--outside-month)").click();

        $("[id=subjectsInput]").setValue(REG_SUBJECT_INPUT);
        $(".subjects-auto-complete__option").click();

        $("#hobbiesWrapper").$$("label").findBy(text(REG_HOBBY_SPORTS)).click();
        $("#hobbiesWrapper").$$("label").findBy(text(REG_HOBBY_READING)).click();
        $("#hobbiesWrapper").$$("label").findBy(text(REG_HOBBY_MUSIC)).click();

        $("[id=uploadPicture]").uploadFromClasspath(REG_PICTURE_NAME);

        $("[id=currentAddress]").setValue(REG_CURRENT_ADDRESS);

        $("[id=state]").click();
        $$("[id^=react-select-3-option]").findBy(text(REG_STATE)).click();

        $("[id=city]").click();
        $$("[id^=react-select-4-option]").findBy(text(REG_CITY)).click();

        $("[id=submit]").click();

        $(".modal-title").shouldHave(text(SUCCESS_MODAL_TITLE));

        $(".table-responsive").$(byText(STUDENT_NAME_LABEL)).parent()
                .shouldHave(text(REG_FULL_NAME));
        $(".table-responsive").$(byText(STUDENT_EMAIL_LABEL)).parent()
                .shouldHave(text(REG_EMAIL));
        $(".table-responsive").$(byText(GENDER_LABEL)).parent()
                .shouldHave(text(REG_MALE_GENDER));
        $(".table-responsive").$(byText(MOBILE_LABEL)).parent()
                .shouldHave(text(REG_MOBILE));
        $(".table-responsive").$(byText(DATE_OF_BIRTH_LABEL)).parent()
                .shouldHave(text(REG_EXPECTED_BIRTH_DATE));
        $(".table-responsive").$(byText(SUBJECTS_LABEL)).parent()
                .shouldHave(text(REG_EXPECTED_SUBJECT));
        $(".table-responsive").$(byText(HOBBIES_LABEL)).parent()
                .shouldHave(text(REG_EXPECTED_HOBBIES));
        $(".table-responsive").$(byText(PICTURE_LABEL)).parent()
                .shouldHave(text(REG_PICTURE_NAME));
        $(".table-responsive").$(byText(ADDRESS_LABEL)).parent()
                .shouldHave(text(REG_CURRENT_ADDRESS));
        $(".table-responsive").$(byText(STATE_AND_CITY_LABEL)).parent()
                .shouldHave(text(REG_EXPECTED_STATE_AND_CITY));
    }

    @Test
    void successfulRequiredFillFormTest() {
        open(PRACTICE_FORM_URL);

        $("[id=firstName]").setValue(REG_FIRST_NAME);
        $("[id=lastName]").setValue(REG_LAST_NAME);
        $("[id=userNumber]").setValue(REG_REQUIRED_MOBILE);

        $("#genterWrapper").$$("label").findBy(text(REG_FEMALE_GENDER)).click();

        $("[id=submit]").click();

        $(".modal-title").shouldHave(text(SUCCESS_MODAL_TITLE));
        $(".table-responsive").$(byText(STUDENT_NAME_LABEL)).parent()
                .shouldHave(text(REG_FULL_NAME));
        $(".table-responsive").$(byText(GENDER_LABEL)).parent()
                .shouldHave(text(REG_FEMALE_GENDER));
        $(".table-responsive").$(byText(MOBILE_LABEL)).parent()
                .shouldHave(text(REG_REQUIRED_MOBILE));
        $(".table-responsive").$(byText(DATE_OF_BIRTH_LABEL)).parent()
                .shouldHave(text(REG_EXPECTED_DEFAULT_BIRTH_DATE));
    }

    @Test
    void invalidSubmitWithEmptyFormTest() {
        open(PRACTICE_FORM_URL);

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
        $("[id=firstName]").shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        $("[id=lastName]").shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
        $("[id=userNumber]").shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
    }

    @Test
    void invalidSubmitWithoutFirstNameTest() {
        open(PRACTICE_FORM_URL);

        $("[id=lastName]").setValue(REG_LAST_NAME);
        $("[id=userNumber]").setValue(REG_MOBILE);
        $("#genterWrapper").$$("label").findBy(text(REG_MALE_GENDER)).click();

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
        $("[id=firstName]").shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
    }

    @Test
    void invalidSubmitWithoutLastNameTest() {
        open(PRACTICE_FORM_URL);

        $("[id=firstName]").setValue(REG_FIRST_NAME);
        $("[id=userNumber]").setValue(REG_MOBILE);
        $("#genterWrapper").$$("label").findBy(text(REG_MALE_GENDER)).click();

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
        $("[id=lastName]").shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
    }

    @Test
    void invalidSubmitWithoutGenderTest() {
        open(PRACTICE_FORM_URL);

        $("[id=firstName]").setValue(REG_FIRST_NAME);
        $("[id=lastName]").setValue(REG_LAST_NAME);
        $("[id=userNumber]").setValue(REG_MOBILE);

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
        $("#genterWrapper").$$("label").findBy(text(REG_MALE_GENDER))
                .shouldHave(cssValue("color", ERROR_GENDER_COLOR));
        $("#genterWrapper").$$("label").findBy(text(REG_FEMALE_GENDER))
                .shouldHave(cssValue("color", ERROR_GENDER_COLOR));
        $("#genterWrapper").$$("label").findBy(text(REG_OTHER_GENDER))
                .shouldHave(cssValue("color", ERROR_GENDER_COLOR));
    }

    @Test
    void invalidSubmitWithInvalidEmailTest() {
        open(PRACTICE_FORM_URL);

        $("[id=firstName]").setValue(REG_FIRST_NAME);
        $("[id=lastName]").setValue(REG_LAST_NAME);
        $("[id=userEmail]").setValue(REG_INVALID_EMAIL);
        $("[id=userNumber]").setValue(REG_MOBILE);
        $("#genterWrapper").$$("label").findBy(text(REG_MALE_GENDER)).click();

        $("[id=submit]").click();

        $(".modal-body").shouldNot(exist);
        $("[id=userEmail]").shouldHave(cssValue("border-color", ERROR_BORDER_COLOR));
    }
}
