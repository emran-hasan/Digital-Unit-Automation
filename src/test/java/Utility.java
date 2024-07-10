import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Utility {
    public static void scroll (WebDriver driver, int scroll){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,"+scroll+")");
    }
}
