package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    private final SelenideElement fullNameInput = $("[id=userName]");
    private final SelenideElement emailInput = $("[id=userEmail]");
    private final SelenideElement currentAddressInput = $("[id=currentAddress]");
    private final SelenideElement permanentAddressInput = $("[id=permanentAddress]");
    private final SelenideElement submitButton = $("[id=submit]");
    private final SelenideElement outputBorder = $("#output .border");
    private final SelenideElement outputName = $("#output #name");
    private final SelenideElement outputEmail = $("#output #email");
    private final SelenideElement outputCurrentAddress = $("#output #currentAddress");
    private final SelenideElement outputPermanentAddress = $("#output #permanentAddress");

    public TextBoxPage openPage(String url) {
        open(url);
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        fullNameInput.setValue(fullName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage submit() {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkOutputFullName(String fullName) {
        outputName.shouldHave(text(fullName));
        return this;
    }

    public TextBoxPage checkOutputEmail(String email) {
        outputEmail.shouldHave(text(email));
        return this;
    }

    public TextBoxPage checkOutputCurrentAddress(String currentAddress) {
        outputCurrentAddress.shouldHave(text(currentAddress));
        return this;
    }

    public TextBoxPage checkOutputPermanentAddress(String permanentAddress) {
        outputPermanentAddress.shouldHave(text(permanentAddress));
        return this;
    }

    public TextBoxPage checkOutputIsNotVisible() {
        outputBorder.shouldNot(exist);
        return this;
    }

    public TextBoxPage checkEmailHasErrorClass(String errorClass) {
        emailInput.shouldHave(cssClass(errorClass));
        return this;
    }
}
