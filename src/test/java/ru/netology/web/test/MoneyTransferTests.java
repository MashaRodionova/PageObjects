package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void test1(){
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(new DataHelper.AuthInfo());
        DashBoardPage dashBoardPage = verificationPage.validVarify(new DataHelper.VerificationInfo());
        int[] tmp =  dashBoardPage.transferFromCard2ToCard1("300");
        Assertions.assertEquals(tmp[0]+300, tmp[2]);
        Assertions.assertEquals(tmp[1]-300, tmp[3]);



    }
}
