package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement count = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement button = $("[data-test-id='action-transfer'] span.button__text");
    private SelenideElement errorMessage = $("[data-test-id='error-notification'] div.notification__content");

    public DashBoardPage makeValidTransfer(String sum, String cardNumber) {
        makeTransfer(sum, cardNumber);
        return new DashBoardPage();
    }

    public void makeTransfer(String sum, String cardNumber) {
        count.setValue(sum);
        from.setValue(cardNumber);
        button.click();
    }

    public void makeTransferWithoutSum(String cardNumber) {
        from.setValue(cardNumber);
        button.click();
    }

    public void makeTransferWithoutCardNumber(String sum) {
        count.setValue(sum);
        button.click();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(Condition.exactText(expectedText)).shouldBe(Condition.visible);
    }

}
