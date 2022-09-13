package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void test1() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(new DataHelper.AuthInfo());
        DashBoardPage dashBoardPage1 = verificationPage.validVarify(new DataHelper.VerificationInfo());
        int firstBalanceCard1 = dashBoardPage1.getCardBalance(dashBoardPage1.getCard1());
        int firstBalanceCard2 = dashBoardPage1.getCardBalance(dashBoardPage1.getCard2());
        MoneyTransferPage moneyTransferPage = dashBoardPage1.replenishCard1();
        DashBoardPage dashBoardPage2 = moneyTransferPage.moneyTransfer("300", new DataHelper.CardInfo().getNumber2());
        int secondBalanceCard1 = dashBoardPage2.getCardBalance(dashBoardPage2.getCard1());
        int secondBalanceCard2 = dashBoardPage2.getCardBalance(dashBoardPage2.getCard2());
        Assertions.assertEquals(firstBalanceCard1 + 300, secondBalanceCard1);
        Assertions.assertEquals(firstBalanceCard2 - 300, secondBalanceCard2);


    }
}
