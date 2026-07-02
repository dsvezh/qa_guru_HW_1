package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("[id=firstName]");
    private final SelenideElement lastNameInput = $("[id=lastName]");
    private final SelenideElement emailInput = $("[id=userEmail]");
    private final SelenideElement mobileInput = $("[id=userNumber]");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement dateOfBirthInput = $("[id=dateOfBirthInput]");
    private final SelenideElement monthSelect = $(".react-datepicker__month-select");
    private final SelenideElement yearSelect = $(".react-datepicker__year-select");
    private final SelenideElement subjectsInput = $("[id=subjectsInput]");
    private final SelenideElement subjectOption = $(".subjects-auto-complete__option");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("[id=uploadPicture]");
    private final SelenideElement currentAddressInput = $("[id=currentAddress]");
    private final SelenideElement stateInput = $("[id=state]");
    private final SelenideElement cityInput = $("[id=city]");
    private final SelenideElement submitButton = $("[id=submit]");
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement modalBody = $(".modal-body");
    private final SelenideElement resultsTable = $(".table-responsive");

    public RegistrationPage openPage(String url) {
        open(url);
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage setMobile(String mobile) {
        mobileInput.setValue(mobile);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject);
        subjectOption.click();
        return this;
    }

    public RegistrationPage setHobbies(String... hobbies) {
        for (String hobby : hobbies) {
            hobbiesWrapper.$(byText(hobby)).click();
        }
        return this;
    }

    public RegistrationPage uploadPicture(String pictureName) {
        uploadPictureInput.uploadFromClasspath(pictureName);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateInput.click();
        $$("[id^=react-select-3-option]").findBy(text(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput.click();
        $$("[id^=react-select-4-option]").findBy(text(city)).click();
        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkSuccessModalTitle(String title) {
        modalTitle.shouldHave(text(title));
        return this;
    }

    public RegistrationPage checkResult(String label, String value) {
        resultsTable.$(byText(label)).parent().shouldHave(text(value));
        return this;
    }

    public RegistrationPage checkModalIsNotVisible() {
        modalBody.shouldNot(exist);
        return this;
    }

    public RegistrationPage checkFirstNameValidationColor(String color) {
        firstNameInput.shouldHave(cssValue("border-color", color));
        return this;
    }

    public RegistrationPage checkLastNameValidationColor(String color) {
        lastNameInput.shouldHave(cssValue("border-color", color));
        return this;
    }

    public RegistrationPage checkMobileValidationColor(String color) {
        mobileInput.shouldHave(cssValue("border-color", color));
        return this;
    }

    public RegistrationPage checkEmailValidationColor(String color) {
        emailInput.shouldHave(cssValue("border-color", color));
        return this;
    }

    public RegistrationPage checkGenderValidationColor(String color, String... genders) {
        for (String gender : genders) {
            genderWrapper.$(byText(gender)).shouldHave(cssValue("color", color));
        }
        return this;
    }
}
