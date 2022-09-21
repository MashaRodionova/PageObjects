package ru.netology.web.data;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Value;

import static com.codeborne.selenide.Selenide.$;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    public static VerificationInfo getCode(AuthInfo authInfo) {
        return new VerificationInfo("12345");
    }

   public static CardInfo getFirstCard(){
      return new CardInfo("5559 0000 0000 0001", $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']"));
   }

    public static CardInfo getSecondCard(){
        return new CardInfo("5559 0000 0000 0002", $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']"));
    }

    @Value
    @AllArgsConstructor
    public static class AuthInfo {
        private String login;
        private String password;


    }

    @Value
    public static class VerificationInfo {
        private String code;
    }

    @Value
    @AllArgsConstructor
    public static class CardInfo {
        private String number;
        private SelenideElement cardID;
    }
}

