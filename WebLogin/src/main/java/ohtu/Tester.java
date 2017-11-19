package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/rokka/Courses/Exercises/Ohjelmistotuotanto/ChromeDriver/chromedriver"); 
  
        WebDriver driver = new ChromeDriver();
        
        driver.get("http://localhost:4567");     
        if(!testLogin(driver,"pekka","akkep")) System.out.println("failed to login with valid credentials");
        driver.get("http://localhost:4567");     
        if(testLogin(driver,"pekka","akkepasd"))System.out.println("succeeded to login with invalid credentials (password)");
        driver.get("http://localhost:4567");     
        Random r = new Random();
        String username = "arto"+r.nextInt(100000);
        String password = "asd"+r.nextInt(100000);
        if(testLogin(driver,username,password))System.out.println("succeeded to login with invalid credentials (username)");
        driver.get("http://localhost:4567");     
        username = "arto"+r.nextInt(100000);
        password = "asd"+r.nextInt(100000);
        if(testCreate(driver,username,password,password)) System.out.println("failed to create account");
        sleep(2);
        WebElement element  = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));     
        element.click();
        sleep(2);
        if(driver.findElements(By.linkText("register new user")).isEmpty()) System.out.println("failed to logout");
        driver.quit();
    }
    

        
    private static boolean testCreate(WebDriver driver,String username, String password1, String password2){   
        sleep(2);        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password1);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password2);
        element = driver.findElement(By.name("signup"));        
        sleep(2);
        element.submit();
        sleep(3);
        if(driver.findElements(By.id("error")).size() > 0) return true;
        return false;
    }
    
    
    
    
    
    private static boolean testLogin(WebDriver driver,String username, String password){
         driver.get("http://localhost:4567");        
        sleep(2);        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));        
        sleep(2);
        element.submit();
        sleep(3);
        if(driver.getCurrentUrl().contains("/ohtu")) return true;
        return false;
    }
    
    
    
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
