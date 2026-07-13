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
        registrationPage.openPage(PRACTICE_FORM_URL)
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
                .checkResult(STUDENT_NAME_LABEL, testData.regFullName)
                .checkResult(STUDENT_EMAIL_LABEL, testData.regEmail)
                .checkResult(GENDER_LABEL, testData.regGender)
                .checkResult(MOBILE_LABEL, testData.regMobile)
                .checkResult(DATE_OF_BIRTH_LABEL, testData.regExpectedBirthDate)
                .checkResult(SUBJECTS_LABEL, testData.regSubject)
                .checkResult(HOBBIES_LABEL, testData.regHobby)
                .checkResult(PICTURE_LABEL, REG_PICTURE_NAME)
                .checkResult(ADDRESS_LABEL, testData.regCurrentAddress)
                .checkResult(STATE_AND_CITY_LABEL, testData.regExpectedStateAndCity);
    }

    @Test
    void successfulRequiredFillFormTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(testData.regFirstName)
                .setLastName(testData.regLastName)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkSuccessModalTitle(SUCCESS_MODAL_TITLE)
                .checkResult(STUDENT_NAME_LABEL, testData.regFullName)
                .checkResult(GENDER_LABEL, testData.regGender)
                .checkResult(MOBILE_LABEL, testData.regMobile)
                .checkResult(DATE_OF_BIRTH_LABEL, testData.regExpectedDefaultBirthDate);
    }

    @Test
    void invalidSubmitWithEmptyFormTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .submit()
                .checkModalIsNotVisible()
                .checkFirstNameValidationColor(ERROR_BORDER_COLOR)
                .checkLastNameValidationColor(ERROR_BORDER_COLOR)
                .checkMobileValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutFirstNameTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setLastName(testData.regLastName)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkModalIsNotVisible()
                .checkFirstNameValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutLastNameTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(testData.regFirstName)
                .setMobile(testData.regMobile)
                .setGender(testData.regGender)
                .submit()
                .checkModalIsNotVisible()
                .checkLastNameValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutGenderTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(testData.regFirstName)
                .setLastName(testData.regLastName)
                .setMobile(testData.regMobile)
                .submit()
                .checkModalIsNotVisible()
                .checkGenderValidationColor(ERROR_GENDER_COLOR, REG_MALE_GENDER, REG_FEMALE_GENDER, REG_OTHER_GENDER);
    }

    @Test
    void invalidSubmitWithInvalidEmailTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
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
