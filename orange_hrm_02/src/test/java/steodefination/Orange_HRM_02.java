package steodefination;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Orange_HRM_02 {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
    }

    @Then("the {string} link should be present on the login page")
    public void theLinkShouldBePresent(String linkText) {
        List<WebElement> resetLink = driver.findElements(By.xpath("//p[text()='Forgot your password? ']"));
        assertTrue("Reset password link is not present on the login page.",
                   !resetLink.isEmpty() && resetLink.get(0).isDisplayed());
    }

    @When("the user clicks the {string} link")
    public void theUserClicksTheLink(String linkText) {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Forgot your password? ']")));
        link.click();
    }

    @Then("the user should be redirected to the password reset page")
    public void theUserShouldBeRedirectedToResetPage() {
        wait.until(ExpectedConditions.urlContains("/requestPasswordResetCode"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/requestPasswordResetCode"));
        System.out.println("Redirected to password reset page: " + currentUrl);
    }

    @Given("the user is on the password reset page")
    public void theUserIsOnThePasswordResetPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Forgot your password? ']"))).click();
        wait.until(ExpectedConditions.urlContains("/requestPasswordResetCode"));
    }

    @When("the user enters a valid username")
    public void theUserEntersAValidUsername() {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.sendKeys("Admin");
    }

    @When("clicks the {string} button for password reset")
    public void clicksTheResetButton(String buttonName) {
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitBtn.click();
    }

    @Then("a password reset link should be sent to the registered email address")
    public void passwordResetLinkSentToEmail() {
        wait.until(ExpectedConditions.urlContains("/sendPasswordReset"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/sendPasswordReset"));

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']")
                )
        );
        assertEquals("Reset Password link sent successfully", successMessage.getText());
        System.out.println("Success message displayed: " + successMessage.getText());
    }

    @When("the user leave empty username")
    public void theUserLeavesUsernameEmpty() {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        usernameField.clear();
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
    }

    @Then("error massage is displayed")
    public void errorMessageIsDisplayed() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-v-957b4417] span.oxd-input-field-error-message")));
        assertEquals("Required", errorMessage.getText());
        System.out.println("Error Message: " + errorMessage.getText());
    	    }

    @When("the user clicks the {string} button")
    public void theUserClicksTheButton(String buttonLabel) {
        WebElement backButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']")));
        backButton.click();
    }

    @Then("the user should be redirected to the login page")
    public void userShouldBeRedirectedToLoginPage() {
        wait.until(ExpectedConditions.urlContains("/login"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/login"));
        System.out.println("Redirected to login page: " + currentUrl);
    }
}
