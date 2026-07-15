package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.TestData;

import static testdata.TestData.*;

public class RegistrationTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    TestData testData = new TestData();

    @Test
    void successfulFullFillFormTest() {
        registrationPage.openPage()
                .setFirstName(testData.regFirstName)
                .setLastName(testData.regLastName)
                .setEmail(testData.regEmail)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .setDateOfBirth(testData.regBirthDay, testData.regBirthMonth, testData.regBirthYear)
                .setSubject(testData.regSubject)
                .setHobbies(testData.regHobby)
                .uploadPicture(REG_PICTURE_NAME)
                .setCurrentAddress(testData.regCurrentAddress)
                .setState(testData.regState)
                .setCity(testData.regCity)
                .submit()
                .checkSuccessModalTitle(SUCCESS_MODAL_TITLE)
                .checkResult("Student Name", testData.regFullName)
                .checkResult("Student Email", testData.regEmail)
                .checkResult("Gender", testData.regGender)
                .checkResult("Mobile", testData.regMobile)
                .checkResult("Date of Birth", testData.regExpectedBirthDate)
                .checkResult("Subjects", testData.regSubject)
                .checkResult("Hobbies", testData.regHobby)
                .checkResult("Picture", REG_PICTURE_NAME)
                .checkResult("Address", testData.regCurrentAddress)
                .checkResult("State and City", testData.regExpectedStateAndCity);
    }

    @Test
    void successfulRequiredFillFormTest() {
        registrationPage.openPage()
                .setFirstName(testData.regFirstName)
                .setLastName(testData.regLastName)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkSuccessModalTitle(SUCCESS_MODAL_TITLE)
                .checkResult("Student Name", testData.regFullName)
                .checkResult("Gender", testData.regGender)
                .checkResult("Mobile", testData.regMobile)
                .checkResult("Date of Birth", testData.regExpectedDefaultBirthDate);
    }

    @Test
    void invalidSubmitWithEmptyFormTest() {
        registrationPage.openPage()
                .submit()
                .checkModalIsNotVisible()
                .checkFirstNameValidationColor(ERROR_BORDER_COLOR)
                .checkLastNameValidationColor(ERROR_BORDER_COLOR)
                .checkMobileValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutFirstNameTest() {
        registrationPage.openPage()
                .setLastName(testData.regLastName)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkModalIsNotVisible()
                .checkFirstNameValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutLastNameTest() {
        registrationPage.openPage()
                .setFirstName(testData.regFirstName)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkModalIsNotVisible()
                .checkLastNameValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutGenderTest() {
        registrationPage.openPage()
                .setFirstName(testData.regFirstName)
                .setLastName(testData.regLastName)
                .setMobile(testData.regMobile)
                .submit()
                .checkModalIsNotVisible()
                .checkGenderValidationColor(ERROR_GENDER_COLOR, REG_MALE_GENDER, REG_FEMALE_GENDER, REG_OTHER_GENDER);
    }

    @Test
    void invalidSubmitWithInvalidEmailTest() {
        registrationPage.openPage()
                .setFirstName(testData.regFirstName)
                .setLastName(testData.regLastName)
                .setEmail(testData.regInvalidEmail)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkModalIsNotVisible()
                .checkEmailValidationColor(ERROR_BORDER_COLOR);
    }
}
