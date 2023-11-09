import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.*;
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
    public void click(WebDriver driver, By by){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }
    public void clear(WebDriver driver, By by){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.clear();
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForTextInElement(By.className("ng-star-inserted"), "Jesper Eriksson");
    }
    @After
    public void teardown(){
        driver.quit();
    }
    @Test
    public void CreateChallenge() {
        click(driver, By.cssSelector(".mat-list > a:nth-child(3)"));
        click(driver, By.cssSelector(".col-lg-12 > button:nth-child(1)"));
        sendKeys(driver, By.cssSelector("#mat-input-0"), "AutomatedTest Challenge");
        sendKeys(driver, By.cssSelector("#mat-input-1"), "This challenge should be deleted after the test is over.");
        sendKeys(driver, By.cssSelector("#mat-input-2"), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fconnect.redhat.com%2Fen%2Fblog%2Fleading-automation-0&psig=AOvVaw0AN5tzT0tJehVjkBsr4dCJ&ust=1699547016110000&source=images&cd=vfe&opi=89978449&ved=0CB");
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(5)"));
        click(driver, By.cssSelector(".modal-footer > button:nth-child(1)"));
        click(driver, By.cssSelector("app-challenge-view.ng-star-inserted:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)"));
        click(driver, By.cssSelector(".mat-warn"));
        click(driver, By.cssSelector(".dialog-confirm > btn-container:nth-child(4) > button:nth-child(1)"));
        String actual = textInElement(driver, By.cssSelector("p.ng-star-inserted"));
        String expected = "Challenge AutomatedTest Challenge has been deleted!";
        assertEquals(expected, actual);
    }

    @Test
    public void JoinComp(){
        click(driver, By.cssSelector(".mat-list > a:nth-child(2)"));
        click(driver, By.cssSelector(".container > div:nth-child(1) > div:nth-child(1) > app-competition-view:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)"));
        click(driver, By.cssSelector("div.team:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(2)"));
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));
        click(driver, By.cssSelector(".modal-footer > mat-button:nth-child(1)"));
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));
        String actual = textInElement(driver, By.cssSelector("div.ng-star-inserted:nth-child(3)"));
        String expected = "Jesper Eriksson keyboard_arrow_down";
        assertEquals(expected, actual);
    }
    @Test
    public void CreateComp() {
        click(driver, By.cssSelector("button.mat-focus-indicator"));
        click(driver, By.cssSelector("button.mat-menu-item:nth-child(2)"));
        click(driver, By.cssSelector(".button-create"));
        sendKeys(driver, By.id("mat-input-5"), "AutomatedTest Comp");
        sendKeys(driver, By.id("mat-input-6"), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.xlent.se%2Ferbjudande%2Frpa&psig=AOvVaw01F9ZdzvoYdTjtfs1Yh4jc&ust=1699602464442000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCIj2zdq2toIDFQAAAAAdAAAAABAE");
        click(driver, By.cssSelector("mat-datepicker-toggle.ng-tns-c117-11"));
        click(driver, By.cssSelector("tr.ng-star-inserted:nth-child(4) > td:nth-child(4)"));
        click(driver, By.cssSelector("mat-datepicker-toggle.ng-tns-c117-12"));
        click(driver, By.cssSelector("tr.ng-star-inserted:nth-child(5) > td:nth-child(5)"));
        sendKeys(driver, By.cssSelector("#mat-input-9"), "This is a competition created with automation and should have been deleted at the end of the test, if this is not the case then ask/tell the tester that something is not working as is intended.");
        click(driver, By.cssSelector("button.mat-stepper-next:nth-child(1)"));
        click(driver, By.cssSelector("button.mat-stepper-next:nth-child(2)"));;
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));
        click(driver, By.cssSelector(".modal-footer > button:nth-child(1)"));
        sendKeys(driver, By.cssSelector(".search-bar"), "AutomatedTest Comp");
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(2)"));
        click(driver, By.cssSelector(".modal-footer > button:nth-child(2)"));
    }

    @Test
    public void EditChallenges() {
        click(driver, By.cssSelector(".mat-list > a:nth-child(3)"));
        click(driver, By.cssSelector("app-challenge-view.ng-star-inserted:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(1)"));
        clear(driver,By.cssSelector("#title"));
        sendKeys(driver,By.cssSelector("#title"),"Dance All Night");
        clear(driver,By.cssSelector("#description"));
        sendKeys(driver,By.cssSelector("#description")," We will all Dance All Night & have Fun");
        click(driver,By.cssSelector("button.mat-focus-indicator:nth-child(2)"));
        click(driver,By.xpath("/html/body/div/div[4]/div/mat-dialog-container/div/btn-container/button[1]"));
        click(driver,By.xpath("/html/body/div/div[2]/div/mat-dialog-container/button"));
        String actual = textInElement(driver,By.cssSelector("app-challenge-view.ng-star-inserted:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1)"));
        String expected = "Dance All Night";
        assertEquals(expected, actual);


    }
}
//  @Test
//   public void NotifTest(){
//        //find out where notifs are displayed
//        //Ask someone familiar with JS how to scroll down in the small overlay window
//        click(driver, By.cssSelector(".mat-list > a:nth-child(4)"));
//        sendKeys(driver, By.cssSelector("#mat-input-3"), "Disregard, automated message");
//        click(driver, By.cssSelector("#mat-slide-toggle-3"));
//        click(driver, By.cssSelector("#mat-select-0"));
//
//
//        sendKeys(driver, By.cssSelector("#mat-input-1"), "Automated test ran in Selenium to the test notification functionality, disregard or contact tester if you have questions");
//        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(4)"));