package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");

    private SelenideElement card1 = DataHelper.getFirstCard().getCardID();
    private SelenideElement buttonCard1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] span.button__text");
    private SelenideElement card2 = DataHelper.getSecondCard().getCardID();
    private SelenideElement buttonCard2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] span.button__text");
    private final String balanceFinish = " р.";
    private final String balanceStart = ", баланс: ";


    public DashBoardPage() {
        heading.shouldBe(Condition.visible);
    }

    public int getFirstCardBalance() {
        String text = card1.getText();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        String text = card2.getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public MoneyTransferPage replenishCard1() {
        card1.shouldBe(Condition.visible);
        buttonCard1.click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage replenishCard2() {
        card2.shouldBe(Condition.visible);
        buttonCard2.click();
        return new MoneyTransferPage();
    }
}
