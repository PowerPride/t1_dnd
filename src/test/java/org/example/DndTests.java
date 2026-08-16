package org.example;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DndTests {

    @Test
    void task1_1_dragToCart() {
        open("http://localhost:8080/");

        String productName = $(".product-card h4").getText();

        $(".product-card")
                .dragAndDrop(to("#open-cart-btn"));

        $("#open-cart-btn").click();

        $("#cart-items")
                .shouldHave(text(productName));
    }

    @Test
    void task1_2_removeFromCart() {
        open("http://localhost:8080/");

        String productName = $(".product-card h4").getText();

        $(".product-card button[data-action='add-to-cart']").click();

        $("#open-cart-btn").click();

        $(".cart-item")
                .shouldHave(text(productName));

        $(".cart-item button[data-action='remove']")
                .click();

        $(".cart-item")
                .shouldNot(exist);
    }
}
