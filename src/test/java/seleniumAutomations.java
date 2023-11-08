import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class seleniumAutomations {
    WebDriver driver;
    WebDriverWait wait;

    public void sendKeys(WebDriver driver, By by, String text) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }
    public void waitForTextInElement(By locator, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }
    public void Click(WebDriver driver, By by){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.click();
    }

    public String textInElement(WebDriver driver, By by){
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        return element.getText();
    }

    @Before
    public void setup(){
        System.setProperty("webdriver.edge.driver", "C:\\Selenium\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*", "--ignore-certificate-errors");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.get("https://nexerboostappadmin.azurewebsites.net/home");
        waitForTextInElement(By.className("ng-star-inserted"), "Jesper Eriksson");
    }
    @After
    public void teardown(){
        driver.quit();
    }
    @Test
    public void OpenChallenges() {
        Click(driver, By.cssSelector(".mat-list > a:nth-child(3)"));
    }

    @Test
    public void JoinComp(){
        Click(driver, By.cssSelector(".mat-list > a:nth-child(2)"));
        Click(driver, By.cssSelector(".container > div:nth-child(1) > div:nth-child(1) > app-competition-view:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)"));
        Click(driver, By.cssSelector("div.team:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(2)"));
        Click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));
        Click(driver, By.cssSelector(".modal-footer > mat-button:nth-child(1)"));
        Click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));

        String actual = textInElement(driver, By.cssSelector("div.ng-star-inserted:nth-child(3)"));
        String expected = "Jesper Eriksson keyboard_arrow_down";
        assertEquals(expected, actual);
    }
}
