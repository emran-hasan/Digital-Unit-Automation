import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalUnit {

    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

    }
    @Test
    public void registration() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String title = driver.getTitle();
        System.out.println(title);

        WebElement name = driver.findElement(By.id("edit-name"));
        WebElement phoneNumber = driver.findElement(By.id("edit-number"));
        WebElement selectDate = driver.findElement(By.id("edit-date"));
        WebElement email = driver.findElement(By.id("edit-email"));
        WebElement textArea = driver.findElement(By.cssSelector("textarea"));
        WebElement uploadFile = driver.findElement(By.cssSelector("[type=file]"));
        WebElement checkBox = driver.findElement(By.cssSelector("[type=checkbox]"));
        WebElement submitButton = driver.findElement(By.id("edit-submit"));

//        Executing Element
        name.sendKeys("Md. Emran Hasan");
        phoneNumber.sendKeys("01712345678");
        selectDate.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
//        Executing Date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);
        selectDate.sendKeys(formattedDate);
        selectDate.sendKeys(Keys.ENTER);

        email.sendKeys("emranhasan906@gmail.com");
        textArea.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam venenatis, dui a faucibus cursus, " +
                "elit tellus rhoncus lacus, eget commodo erat ante eget sapien. Maecenas et ipsum lectus." +
                " Morbi sed enim tincidunt urna molestie suscipit non sodales dui. Nullam laoreet vel nulla sed scelerisque.");

         Utility.scroll(driver,400);
        uploadFile.sendKeys(System.getProperty("user.dir")+"./src/test/resources/img/SDET-Career-Roadmap.jpg");
        Thread.sleep(3000);
        checkBox.click();
        submitButton.click();

        WebElement successMessage = driver.findElement(By.id("block-pagetitle-2"));
        assertEquals("Thank you for your submission!", successMessage.getText());

    }
    @AfterAll
    public void quitProject(){
        driver.quit();
    }

}
