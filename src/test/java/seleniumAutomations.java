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
        //Combines wait, locator and text to send in a single line of code
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }
    public void waitForTextInElement(By locator, String expectedText) {
        //As of now the site refreshes automatically once because it first loads without a logged in user,
        // and then with whatever user is cached, this function is used ONCE in the very start to verify that the site
        // is done refreshing before continuing with the rest of the tests
        // "expectedText" NEEDS TO BE CHANGED TO MATCH YOUR ACCOUNT NAME OTHERWISE IT WILL GET STUCK
        // might be able to make this generic depending on if there's text there at all pre-refresh
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }
    public void click(WebDriver driver, By by){
        //Combines wait, locator and click for cleaner looking and shorter code
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }
    public void clear(WebDriver driver, By by){
        //Combines wait, locator and clear,
        // useful for when input fields have placeholder text that gets in the way of sendKeys
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.clear();
    }

    public String textInElement(WebDriver driver, By by){
        //Gives the site time to load before fetching any text in an element pointed out by a locator,
        // useful for assertions
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element.getText();
    }

    public void BAfuncFindingTeam(String targetText, By BtnLocator){
        //"Band-Aid Function" When trying to join a team a lot of the locators are the same which gives me two options in automation,
        // work around the flawed design by writing a specific function for just this test OR hardcoding the locators which will work
        // as long no one else ever uses this page ever again, which defeats the point. This function is meant to look in the list of
        // teams and look for a specific team name and then click the button that is present in that particular class.
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By divLocator = By.xpath("//div[@class='team-info' and contains(text(), '" + targetText + "')]");
        WebElement divElement = wait.until(ExpectedConditions.presenceOfElementLocated(divLocator));
        WebElement buttonElement = divElement.findElement(BtnLocator);
        buttonElement.click();
    }

    @Before
    public void setup(){
        System.setProperty("webdriver.edge.driver", "C:\\Selenium\\msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*", "--ignore-certificate-errors");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.get("****");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForTextInElement(By.className("ng-star-inserted"), "Jesper Eriksson");
    }
    @After
    public void teardown(){
        driver.quit();
    }
    @Test
    public void CreateChallenge() {
        //Basic test going from home page, to the challenge page, creating a challenge, then deleting it.
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
        //Basic test going from home page to competition, clicking join, then leaving the team.
        click(driver, By.cssSelector(".mat-list > a:nth-child(2)"));
        click(driver, By.cssSelector(".container > div:nth-child(1) > div:nth-child(1) > app-competition-view:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)"));
        click(driver, By.cssSelector("div.team:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(2)"));
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));
        click(driver, By.cssSelector(".modal-footer > mat-button:nth-child(1)"));
        click(driver, By.cssSelector("button.mat-focus-indicator:nth-child(3)"));
        String actual = textInElement(driver, By.cssSelector("div.ng-star-inserted:nth-child(3)"));
        String expected = "****";
        assertEquals(expected, actual);
    }
    @Test
    public void CreateComp() {
        //Test starts at homepage, goes to the admin page, creates a competition,
        // uses the searchbar to find it at the bottom of the list and then deletes it.
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
    @Test
    //Test is non-functional, as the list can't be easily navigated
    public void CreateTeam() throws InterruptedException {
        String teamName = "Team Selenium";
        click(driver, By.cssSelector(".mat-list > a:nth-child(2)"));
        click(driver, By.cssSelector(".container > div:nth-child(1) > div:nth-child(1) > app-competition-view:nth-child(1) > div:nth-child(1) > div:nth-child(3) > button:nth-child(2)"));
        click(driver, By.cssSelector(".create-team-option > button:nth-child(2)"));
        sendKeys(driver, By.xpath("//*[@id=\"mat-input-0\"]"), teamName);
        sendKeys(driver, By.cssSelector("#mat-input-2"), " 12345");
        sendKeys(driver, By.cssSelector("#mat-input-3"), " This is just a Test,will be deleted later");
        click(driver, By.cssSelector(".mat-dialog-actions > button:nth-child(2)"));
        click(driver, By.cssSelector("#mat-dialog-2 > app-basic-dialog > div > div.mat-dialog-actions > button"));
        click(driver, By.xpath("/html/body/app-root/app-main-nav/mat-sidenav-container/mat-sidenav-content/div/app-competition/div[2]/div/div[1]/app-competition-view/div/div[3]/button[2]"));
        BAfuncFindingTeam(teamName, By.className("mat-focus-indicator"));

    }
}
