import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DragAndDropTests {

    private final SelenideElement a = $("#column-a header");
    private final SelenideElement b = $("#column-b header");

    @BeforeAll
    static void setBrowserConfig() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }

    @Test
    void moveElementByActionsTest() {
        open("/drag_and_drop");
        actions().clickAndHold(a).moveToElement(b).release().perform();
        a.shouldHave(text("B"));
        b.shouldHave(text("A"));
    }

    @Test
    void moveElementByDragAndDropTest() {
        open("/drag_and_drop");
        a.dragAndDrop(to(b));
        a.shouldHave(text("B"));
        b.shouldHave(text("A"));
    }
}
