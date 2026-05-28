import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SimpleRegistrationForm extends TestBase {

    @Test
    void successfulFullFillFormTest() {
        open("/text-box");

        // ---- Личные данные ----
        $("[id=userName]").setValue("Ivanov Ivan Ivanovich");
        $("[id=userEmail]").setValue("og@vanya.ru");
        $("[id=currentAddress]").setValue("35 Marshal Rybalko Street, Perm, Russia");
        $("[id=permanentAddress]").setValue("27 Lenin Street, Moscow, Russia");

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка вывода значений полей ----
        $("#output #name").shouldHave(text("Ivanov Ivan Ivanovich"));
        $("#output #email").shouldHave(text("og@vanya.ru"));
        $("#output #currentAddress").shouldHave(text("35 Marshal Rybalko Street, Perm, Russia"));
        $("#output #permanentAddress").shouldHave(text("27 Lenin Street, Moscow, Russia"));
    }

    @Test
    void invalidEmailTest() {
        open("/text-box");

        // ---- Ввод некорректного email ----
        $("[id=userName]").setValue("Ivanov Ivan Ivanovich");
        $("[id=userEmail]").setValue("og-vanya.ru");
        $("[id=currentAddress]").setValue("35 Marshal Rybalko Street, Perm, Russia");
        $("[id=permanentAddress]").setValue("27 Lenin Street, Moscow, Russia");

        // ---- Отправка формы ----
        $("[id=submit]").click();

        // ---- Проверка: введённые значения внизу не отобразились, поле email подсвечено красным ----
        $("#output .border").shouldNot(exist);
        $("[id=userEmail]").shouldHave(cssClass("field-error"));
    }
}
