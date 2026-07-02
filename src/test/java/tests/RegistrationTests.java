package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static testdata.TestData.*;

public class RegistrationTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulFullFillFormTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(REG_FIRST_NAME)
                .setLastName(REG_LAST_NAME)
                .setEmail(REG_EMAIL)
                .setMobile(REG_MOBILE)
                .setGender(REG_MALE_GENDER)
                .setDateOfBirth(REG_BIRTH_DAY, REG_BIRTH_MONTH, REG_BIRTH_YEAR)
                .setSubject(REG_SUBJECT_INPUT)
                .setHobbies(REG_HOBBY_SPORTS, REG_HOBBY_READING, REG_HOBBY_MUSIC)
                .uploadPicture(REG_PICTURE_NAME)
                .setCurrentAddress(REG_CURRENT_ADDRESS)
                .setState(REG_STATE)
                .setCity(REG_CITY)
                .submit()
                .checkSuccessModalTitle(SUCCESS_MODAL_TITLE)
                .checkResult(STUDENT_NAME_LABEL, REG_FULL_NAME)
                .checkResult(STUDENT_EMAIL_LABEL, REG_EMAIL)
                .checkResult(GENDER_LABEL, REG_MALE_GENDER)
                .checkResult(MOBILE_LABEL, REG_MOBILE)
                .checkResult(DATE_OF_BIRTH_LABEL, REG_EXPECTED_BIRTH_DATE)
                .checkResult(SUBJECTS_LABEL, REG_EXPECTED_SUBJECT)
                .checkResult(HOBBIES_LABEL, REG_EXPECTED_HOBBIES)
                .checkResult(PICTURE_LABEL, REG_PICTURE_NAME)
                .checkResult(ADDRESS_LABEL, REG_CURRENT_ADDRESS)
                .checkResult(STATE_AND_CITY_LABEL, REG_EXPECTED_STATE_AND_CITY);
    }

    @Test
    void successfulRequiredFillFormTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(REG_FIRST_NAME)
                .setLastName(REG_LAST_NAME)
                .setMobile(REG_REQUIRED_MOBILE)
                .setGender(REG_FEMALE_GENDER)
                .submit()
                .checkSuccessModalTitle(SUCCESS_MODAL_TITLE)
                .checkResult(STUDENT_NAME_LABEL, REG_FULL_NAME)
                .checkResult(GENDER_LABEL, REG_FEMALE_GENDER)
                .checkResult(MOBILE_LABEL, REG_REQUIRED_MOBILE)
                .checkResult(DATE_OF_BIRTH_LABEL, REG_EXPECTED_DEFAULT_BIRTH_DATE);
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
                .setLastName(REG_LAST_NAME)
                .setMobile(REG_MOBILE)
                .setGender(REG_MALE_GENDER)
                .submit()
                .checkModalIsNotVisible()
                .checkFirstNameValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutLastNameTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(REG_FIRST_NAME)
                .setMobile(REG_MOBILE)
                .setGender(REG_MALE_GENDER)
                .submit()
                .checkModalIsNotVisible()
                .checkLastNameValidationColor(ERROR_BORDER_COLOR);
    }

    @Test
    void invalidSubmitWithoutGenderTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(REG_FIRST_NAME)
                .setLastName(REG_LAST_NAME)
                .setMobile(REG_MOBILE)
                .submit()
                .checkModalIsNotVisible()
                .checkGenderValidationColor(ERROR_GENDER_COLOR, REG_MALE_GENDER, REG_FEMALE_GENDER, REG_OTHER_GENDER);
    }

    @Test
    void invalidSubmitWithInvalidEmailTest() {
        registrationPage.openPage(PRACTICE_FORM_URL)
                .setFirstName(REG_FIRST_NAME)
                .setLastName(REG_LAST_NAME)
                .setEmail(REG_INVALID_EMAIL)
                .setMobile(REG_MOBILE)
                .setGender(REG_MALE_GENDER)
                .submit()
                .checkModalIsNotVisible()
                .checkEmailValidationColor(ERROR_BORDER_COLOR);
    }
}
