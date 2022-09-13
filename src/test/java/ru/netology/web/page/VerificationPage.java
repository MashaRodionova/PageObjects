package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement codeButton = $("[data-test-id='action-verify'] span.button__text");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public DashBoardPage validVarify(DataHelper.VerificationInfo verificationInfo) {
        codeField.setValue(verificationInfo.getCode());
        codeButton.click();
        return new DashBoardPage();
    }

}
