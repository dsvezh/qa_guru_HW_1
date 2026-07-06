package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsModalComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultsModalComponent resultsModalComponent = new ResultsModalComponent();

    private final SelenideElement firstNameInput = $("[id=firstName]");
    private final SelenideElement lastNameInput = $("[id=lastName]");
    private final SelenideElement emailInput = $("[id=userEmail]");
    private final SelenideElement mobileInput = $("[id=userNumber]");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement dateOfBirthInput = $("[id=dateOfBirthInput]");
    private final SelenideElement subjectsInput = $("[id=subjectsInput]");
    private final SelenideElement subjectOption = $(".subjects-auto-complete__option");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("[id=uploadPicture]");
    private final SelenideElement currentAddressInput = $("[id=currentAddress]");
    private final SelenideElement stateInput = $("[id=state]");
    private final SelenideElement cityInput = $("[id=city]");
    private final SelenideElement submitButton = $("[id=submit]");

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
        calendarComponent.setDate(day, month, year);
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
        resultsModalComponent.checkTitle(title);
        return this;
    }

    public RegistrationPage checkResult(String label, String value) {
        resultsModalComponent.checkResult(label, value);
        return this;
    }

    public RegistrationPage checkModalIsNotVisible() {
        resultsModalComponent.checkIsNotVisible();
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
