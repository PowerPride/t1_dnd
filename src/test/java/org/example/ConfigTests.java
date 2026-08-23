package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ConfigTests {

    @BeforeAll
    static void setUp() {
        Configuration.timeout
                = Long.parseLong(TestConfig.get("element.timeout"));

        System.out.println("Stand URL: " + TestConfig.get("base.url"));
        System.out.println("API URL: " + TestConfig.get("api.url"));
        System.out.println("Timeout: " + TestConfig.get("element.timeout"));
        System.out.println("Logging: " + TestConfig.get("logging.mode"));
        System.out.println("Product name: " + TestConfig.get("product.name"));
        System.out.println("Product price: " + TestConfig.get("product.price"));
    }

    @Test
    void task1_configTest(){
        String baseUrl = TestConfig.get("base.url");
        String apiUrl = TestConfig.get("api.url");

        String username = TestConfig.get("admin.username");
        String password = TestConfig.get("admin.password");

        String productName
                = TestConfig.get("product.name") + " " + System.currentTimeMillis();

        String productPrice
                = TestConfig.get("product.price");

        open(baseUrl + "/admin");

        $("#username").sendKeys(username);
        $("#password").sendKeys(password);
        $("button[type='submit']").click();

        $("#n-name").sendKeys(productName);
        $("#n-price").sendKeys(productPrice);
        $("#add-btn").click();

        open(baseUrl);

        $(".product-card[data-name='" + productName + "']")
                .shouldBe(exist);
        open(apiUrl + "/goods/list?page=0&size=20");

        $("body").shouldHave(text("goods"));

    }
}
