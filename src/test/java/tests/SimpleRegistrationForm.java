package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.TestData;

import static testdata.TestData.*;

public class SimpleRegistrationForm extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();
    TestData testData = new TestData();

    @Test
    void successfulFullFillFormTest() {
        textBoxPage.openPage()
                .setFullName(testData.textBoxFullName)
                .setEmail(testData.textBoxEmail)
                .setCurrentAddress(testData.textBoxCurrentAddress)
                .setPermanentAddress(testData.textBoxPermanentAddress)
                .submit()
                .checkOutputFullName(testData.textBoxFullName)
                .checkOutputEmail(testData.textBoxEmail)
                .checkOutputCurrentAddress(testData.textBoxCurrentAddress)
                .checkOutputPermanentAddress(testData.textBoxPermanentAddress);
    }

    @Test
    void invalidEmailTest() {
        textBoxPage.openPage()
                .setFullName(testData.textBoxFullName)
                .setEmail(testData.textBoxInvalidEmail)
                .setCurrentAddress(testData.textBoxCurrentAddress)
                .setPermanentAddress(testData.textBoxPermanentAddress)
                .submit()
                .checkOutputIsNotVisible()
                .checkEmailHasErrorClass(FIELD_ERROR_CLASS);
    }

    @Test
    void invalidEmailWithSpacesTest() {
        textBoxPage.openPage()
                .setFullName(testData.textBoxFullName)
                .setEmail(testData.textBoxInvalidEmailWithSpaces)
                .setCurrentAddress(testData.textBoxCurrentAddress)
                .setPermanentAddress(testData.textBoxPermanentAddress)
                .submit()
                .checkOutputIsNotVisible()
                .checkEmailHasErrorClass(FIELD_ERROR_CLASS);
    }
}
