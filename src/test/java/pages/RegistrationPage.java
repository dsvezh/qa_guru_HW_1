package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsModalComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private static final String PATH = "/automation-practice-form";

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultsModalComponent resultsModalComponent = new ResultsModalComponent();

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement mobileInput = $("#userNumber");
    private final SelenideElement genderWrapper = $("#genterWrapper");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement subjectOption = $(".subjects-auto-complete__option");
    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");
    private final SelenideElement submitButton = $("#submit");
    private final ElementsCollection stateOptions = $$("[id^=react-select-3-option]");
    private final ElementsCollection cityOptions = $$("[id^=react-select-4-option]");

    public RegistrationPage openPage() {
        open(PATH);
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
        stateOptions.findBy(text(state)).click();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput.click();
        cityOptions.findBy(text(city)).click();
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
