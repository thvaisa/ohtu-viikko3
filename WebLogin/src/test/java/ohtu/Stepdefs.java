package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    


    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given to create account$")
    public void username_and_password_are_given_to_create_account(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        createAccount(arg1, arg2, arg2);
    }


    
    
    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Then("^Then system will respond \"([^\"]*)\"$")
    public void then_system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }     
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseUrl);
        SelectNewAccount();
        try{ Thread.sleep(120); } catch(Exception e){}
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try{ Thread.sleep(120); } catch(Exception e){}
        createAccount(arg1, arg2, arg2);
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given and non matching password confirmation are entered$")
    public void username_and_password_are_given_and_non_matching_password_confirmation_are_entered(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        createAccount(arg1, arg2, arg2+"asdasd");
    }

    
    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(driver.findElements(By.id("error")).isEmpty());
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageHasContent(arg1);
    }

 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
 
   
    private void SelectNewAccount(){          
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
    
    private void createAccount(String username, String password1, String password2){          
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password1);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password2);
        element = driver.findElement(By.name("signup"));  
        element.submit();
    }
    
    
    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseUrl);
        SelectNewAccount(); 
        createAccount(arg1, arg2, arg2);
        driver.get(baseUrl);
    }

    @Then("^user gets in$")
    public void user_gets_in() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageHasContent("Ohtu Application main page");
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseUrl);
        SelectNewAccount(); 
        createAccount(arg1, arg2, arg2);
        driver.get(baseUrl);
    }

    @When("^try login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void try_login_with_username_and_password(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        logInWith(arg1, arg2);
    }

    @Then("^user does not get in$")
    public void user_does_not_get_in() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        pageHasContent("invalid username or password");
    }


    
    
    
    
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
}
