package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement count = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement button = $("[data-test-id='action-transfer'] span.button__text");

    public DashBoardPage moneyTransfer(String sum, String cardNumber) {
        count.setValue(sum);
        from.setValue(cardNumber);
        button.click();
        return new DashBoardPage();
    }

}
