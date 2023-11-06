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
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class SeleniumAutomations {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void Setup(){
        System.setProperty("webdriver.edge.driver", "C:\\Selenium\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*", "--ignore-certificate-errors");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.get("https://nexerboostappadmin.azurewebsites.net/home");
    }
    @After
    public void TearDown(){
        driver.quit();
    }

    public static WebElement waitForElementVisibility(By locator) {
        // Set the maximum time to wait for the element
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the element to be visible on the page
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private void sendKeys(WebDriver driver, By by, String text) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }
    @Test
    public void OpenChallenges(){

    }

}
