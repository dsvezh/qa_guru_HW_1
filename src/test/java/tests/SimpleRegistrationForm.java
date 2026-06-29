package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;

public class SimpleRegistrationForm extends TestBase {

    @Test
    void successfulFullFillFormTest() {
        open(TEXT_BOX_URL);

        $("[id=userName]").setValue(TEXT_BOX_FULL_NAME);
        $("[id=userEmail]").setValue(TEXT_BOX_EMAIL);
        $("[id=currentAddress]").setValue(TEXT_BOX_CURRENT_ADDRESS);
        $("[id=permanentAddress]").setValue(TEXT_BOX_PERMANENT_ADDRESS);

        $("[id=submit]").click();

        $("#output #name").shouldHave(text(TEXT_BOX_FULL_NAME));
        $("#output #email").shouldHave(text(TEXT_BOX_EMAIL));
        $("#output #currentAddress").shouldHave(text(TEXT_BOX_CURRENT_ADDRESS));
        $("#output #permanentAddress").shouldHave(text(TEXT_BOX_PERMANENT_ADDRESS));
    }

    @Test
    void invalidEmailTest() {
        open(TEXT_BOX_URL);

        $("[id=userName]").setValue(TEXT_BOX_FULL_NAME);
        $("[id=userEmail]").setValue(TEXT_BOX_INVALID_EMAIL);
        $("[id=currentAddress]").setValue(TEXT_BOX_CURRENT_ADDRESS);
        $("[id=permanentAddress]").setValue(TEXT_BOX_PERMANENT_ADDRESS);

        $("[id=submit]").click();

        $("#output .border").shouldNot(exist);
        $("[id=userEmail]").shouldHave(cssClass(FIELD_ERROR_CLASS));
    }

    @Test
    void invalidEmailWithSpacesTest() {
        open(TEXT_BOX_URL);

        $("[id=userName]").setValue(TEXT_BOX_FULL_NAME);
        $("[id=userEmail]").setValue(TEXT_BOX_INVALID_EMAIL_WITH_SPACES);
        $("[id=currentAddress]").setValue(TEXT_BOX_CURRENT_ADDRESS);
        $("[id=permanentAddress]").setValue(TEXT_BOX_PERMANENT_ADDRESS);

        $("[id=submit]").click();

        $("#output .border").shouldNot(exist);
        $("[id=userEmail]").shouldHave(cssClass(FIELD_ERROR_CLASS));
    }
}
