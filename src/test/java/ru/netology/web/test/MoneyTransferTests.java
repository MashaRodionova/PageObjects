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
    void shouldReplenishFirstCard() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage1 = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        int firstBalanceCard1 = dashBoardPage1.getFirstCardBalance();
        int firstBalanceCard2 = dashBoardPage1.getSecondCardBalance();
        int sum = DataHelper.generateValidAmount(firstBalanceCard2);
        MoneyTransferPage moneyTransferPage = dashBoardPage1.replenishCard1();
        DashBoardPage dashBoardPage2 = moneyTransferPage.makeValidTransfer(Integer.toString(sum), DataHelper.getSecondCard().getNumber());
        int secondBalanceCard1 = dashBoardPage2.getFirstCardBalance();
        int secondBalanceCard2 = dashBoardPage2.getSecondCardBalance();
        Assertions.assertEquals(firstBalanceCard1 + sum, secondBalanceCard1);
        Assertions.assertEquals(firstBalanceCard2 - sum, secondBalanceCard2);
    }

    @Test
    void shouldReplenishSecondCard() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage1 = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        int firstBalanceCard1 = dashBoardPage1.getFirstCardBalance();
        int firstBalanceCard2 = dashBoardPage1.getSecondCardBalance();
        int sum = DataHelper.generateValidAmount(firstBalanceCard1);
        MoneyTransferPage moneyTransferPage = dashBoardPage1.replenishCard2();
        DashBoardPage dashBoardPage2 = moneyTransferPage.makeValidTransfer(Integer.toString(sum), DataHelper.getFirstCard().getNumber());
        int secondBalanceCard1 = dashBoardPage2.getFirstCardBalance();
        int secondBalanceCard2 = dashBoardPage2.getSecondCardBalance();
        Assertions.assertEquals(firstBalanceCard1 - sum, secondBalanceCard1);
        Assertions.assertEquals(firstBalanceCard2 + sum, secondBalanceCard2);
    }

    @Test
    void shouldThrowError() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        int firstBalanceCard2 = dashBoardPage.getSecondCardBalance();
        int sum = DataHelper.generateInvalidAmount(firstBalanceCard2);
        MoneyTransferPage moneyTransferPage = dashBoardPage.replenishCard1();
        moneyTransferPage.makeTransfer(Integer.toString(sum), DataHelper.getSecondCard().getNumber());
        moneyTransferPage.findErrorMessage("Ошибка! Попытка перевода суммы, превышающей лимит остатка на карте списания");

    }

    @Test
    void shouldThrowErrorAboutEmptyFieldCardNumber() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        int firstBalanceCard2 = dashBoardPage.getSecondCardBalance();
        int sum = DataHelper.generateValidAmount(firstBalanceCard2);
        MoneyTransferPage moneyTransferPage = dashBoardPage.replenishCard1();
        moneyTransferPage.makeTransferWithoutCardNumber(Integer.toString(sum));
        moneyTransferPage.findErrorMessage("Ошибка! Произошла ошибка");
    }

    @Test
    void shouldThrowErrorAboutEmptyFieldSum() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        MoneyTransferPage moneyTransferPage = dashBoardPage.replenishCard1();
        moneyTransferPage.makeTransferWithoutSum(DataHelper.getSecondCard().getNumber());
        moneyTransferPage.findErrorMessage("Ошибка! Укажите сумму перевода");
    }

    @Test
    void shouldThrowErrorAboutNullFieldSum() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        int sum = 0;
        MoneyTransferPage moneyTransferPage = dashBoardPage.replenishCard1();
        moneyTransferPage.makeValidTransfer(Integer.toString(sum), DataHelper.getSecondCard().getNumber());
        moneyTransferPage.findErrorMessage("Ошибка! Укажите сумму перевода");
    }

    @Test
    void shouldThrowErrorAboutWrongCardNumber() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        DashBoardPage dashBoardPage1 = verificationPage.validVarify(DataHelper.getCode(DataHelper.getAuthInfo()));
        int firstBalanceCard2 = dashBoardPage1.getSecondCardBalance();
        int sum = DataHelper.generateValidAmount(firstBalanceCard2);
        MoneyTransferPage moneyTransferPage = dashBoardPage1.replenishCard1();
        moneyTransferPage.makeValidTransfer(Integer.toString(sum), DataHelper.getInvalidCard().getNumber());
        moneyTransferPage.findErrorMessage("Ошибка! Произошла ошибка");
    }

    @Test
    void shouldNotGetVerificationPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.invalidLogin(DataHelper.getOtherAuthInfo(DataHelper.getAuthInfo()));
        loginPage.findErrorMessage("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void shouldNotGetDashboardPage() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        verificationPage.invalidVarify(DataHelper.getWrongCode(DataHelper.getAuthInfo()));
        verificationPage.findErrorMessage("Ошибка! Неверно указан код! Попробуйте ещё раз.");

    }

}
