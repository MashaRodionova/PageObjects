package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private SelenideElement headingH1 = $("[data-test-id='dashboard'] h1");
    private SelenideElement card1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement buttonCard1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] span.button__text");
    private SelenideElement card2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement buttonCard2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] span.button__text");
    private final String balanceStart = ", баланс: ";
    private final String balanceFinish = " р.\n";

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

    public int[] transferFromCard2ToCard1(String sum) {
        int [] indexesOfAmount;
        int tmp1 = getFirstCardBalance();
        int tmp2 = getSecondCardBalance();
        buttonCard1.click();
        $("[data-test-id='amount'] input").setValue(sum);
        $("[data-test-id='from'] input").setValue(new DataHelper.CardInfo().getNumber2());
        $("[data-test-id='action-transfer'] span.button__text").click();
        int tmp3 = getFirstCardBalance();
        int tmp4 = getSecondCardBalance();
        indexesOfAmount = new int[]{tmp1, tmp2, tmp3, tmp4};

        return indexesOfAmount;
    }

    public int transferFromCard1ToCard2(String sum) {
        buttonCard1.click();
        $("[data-test-id='amount'] input").setValue(sum);
        $("[data-test-id='from'] input").setValue(new DataHelper.CardInfo().getNumber1());
        //$("[data-test-id='to'] input").shouldHave(Condition.exactText("**** **** **** 0002"));
        $("[data-test-id='action-transfer'] span.button__text").click();
        return getSecondCardBalance();
    }
}
