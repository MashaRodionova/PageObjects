package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("div.Login_loginForm__1Hg13 input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login'] span.button__text");

    public VerificationPage validLogin (DataHelper.AuthInfo info){
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}
