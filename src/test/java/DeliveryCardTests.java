import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

class DeliveryCardTests {

    @Test
    void shouldTest1() {
        Configuration.holdBrowserOpen = true;
        String CorrectDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("input[type=text]").setValue("Нижний Новгород");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(CorrectDate);
        $("[data-test-id=name] input").setValue("София Августа Фредерика Ангальт-Цербстская");
        $("[data-test-id=phone] input").setValue("+79876548298");
        $x("//*[text()='Я соглашаюсь с условиями обработки и использования моих персональных данных']").click();
        $x("//*[text()='Забронировать']").click();
        $("[data-test-id=notification]").should(text("Встреча успешно забронирована на " + CorrectDate), Duration.ofSeconds(15));
    }
}