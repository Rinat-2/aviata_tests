package tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AviataNegativeTests extends TestBase {
    @Test
    void simpleUINegativeTest(){
        Configuration.timeout = 15000;
        step ("Заходим на главную страницу", () -> {
            open("https://aviata.kz/");
        });
        step("Нажимаем на предлагаемые популярные направления",() -> {
            $$(".ux-pop-orig").find(visible).sibling(0).click();
            $$(".ux-pop-dest").find(visible).sibling(0).click();
        });

        step("Выбираем дату поездки", () -> {
            $("#desktop-one-way-from-date").click();
            $(".ui-datepicker-today").click();
        });

        step("Нажимаем на кнопку найти билеты", () -> {
            $$(".ux-main-search").find(visible).click();
        });

        step("Проверяем на правильность введенных городов", () -> {
            $((byName("from"))).shouldHave(value("Шымкент"));
            $((byName("to"))).shouldHave(value("Алматы"));
        });

        closeWebDriver();
    }
}

