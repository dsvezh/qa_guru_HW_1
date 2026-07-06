package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsModalComponent {

    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement modalBody = $(".modal-body");
    private final SelenideElement resultsTable = $(".table-responsive");

    public void checkTitle(String title) {
        modalTitle.shouldHave(text(title));
    }

    public void checkResult(String label, String value) {
        resultsTable.$(byText(label)).parent().shouldHave(text(value));
    }

    public void checkIsNotVisible() {
        modalBody.shouldNot(exist);
    }
}
