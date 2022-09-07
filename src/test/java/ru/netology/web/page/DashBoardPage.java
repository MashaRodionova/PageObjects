package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private SelenideElement card1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement buttonCard1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] span.button__text");
    private SelenideElement card2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement buttonCard2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] span.button__text");

    public DashBoardPage() {
        heading.shouldBe(Condition.visible);
    }

    
}
