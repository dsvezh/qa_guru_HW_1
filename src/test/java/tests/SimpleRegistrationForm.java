package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static testdata.TestData.*;

public class SimpleRegistrationForm extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successfulFullFillFormTest() {
        textBoxPage.openPage(TEXT_BOX_URL)
                .setFullName(TEXT_BOX_FULL_NAME)
                .setEmail(TEXT_BOX_EMAIL)
                .setCurrentAddress(TEXT_BOX_CURRENT_ADDRESS)
                .setPermanentAddress(TEXT_BOX_PERMANENT_ADDRESS)
                .submit()
                .checkOutputFullName(TEXT_BOX_FULL_NAME)
                .checkOutputEmail(TEXT_BOX_EMAIL)
                .checkOutputCurrentAddress(TEXT_BOX_CURRENT_ADDRESS)
                .checkOutputPermanentAddress(TEXT_BOX_PERMANENT_ADDRESS);
    }

    @Test
    void invalidEmailTest() {
        textBoxPage.openPage(TEXT_BOX_URL)
                .setFullName(TEXT_BOX_FULL_NAME)
                .setEmail(TEXT_BOX_INVALID_EMAIL)
                .setCurrentAddress(TEXT_BOX_CURRENT_ADDRESS)
                .setPermanentAddress(TEXT_BOX_PERMANENT_ADDRESS)
                .submit()
                .checkOutputIsNotVisible()
                .checkEmailHasErrorClass(FIELD_ERROR_CLASS);
    }

    @Test
    void invalidEmailWithSpacesTest() {
        textBoxPage.openPage(TEXT_BOX_URL)
                .setFullName(TEXT_BOX_FULL_NAME)
                .setEmail(TEXT_BOX_INVALID_EMAIL_WITH_SPACES)
                .setCurrentAddress(TEXT_BOX_CURRENT_ADDRESS)
                .setPermanentAddress(TEXT_BOX_PERMANENT_ADDRESS)
                .submit()
                .checkOutputIsNotVisible()
                .checkEmailHasErrorClass(FIELD_ERROR_CLASS);
    }
}
