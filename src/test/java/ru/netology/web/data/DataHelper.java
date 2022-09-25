package ru.netology.web.data;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Random;

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
      return new CardInfo("5559 0000 0000 0001");
   }

    public static CardInfo getSecondCard(){
        return new CardInfo("5559 0000 0000 0002");
    }

    public static CardInfo getInvalidCard(){
        return new CardInfo("5559 0000 0000 3333");
    }

    public static int generateValidAmount(int balance){
       return new Random().nextInt(Math.abs(balance)) + 1;
    }

    public static int generateInvalidAmount(int balance){
        return Math.abs(balance) + new Random().nextInt(10000);
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;


    }

    @Value
    public static class VerificationInfo {
        private String code;
    }

    @Value
    public static class CardInfo {
        private String number;
    }
}

