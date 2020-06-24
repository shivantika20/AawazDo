

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AwaazDoUI {
    public static void main(String args[]) throws InterruptedException {
        //Setting up chrome driver
        System.setProperty("webdriver.chrome.driver", "/Users/shivantika.t/Downloads/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //adding arguments to ignore any notifications
        options.addArguments("['--disable-web-security', '--user-data-dir', '--allow-running-insecure-content','--disable-notifications' ]");
        //opening chrome
        WebDriver driver = new ChromeDriver(options);
        //waiting for max 15seconds
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //opening chrome and navigating to the site
        driver.navigate().to("http://qainterview.pythonanywhere.com/");
        //maximizing window
        driver.manage().window().maximize();
        //waiting for 3 seconds, before fetching the title and printing
        Thread.sleep(3000);
        String title = driver.getTitle();
        System.out.println(title);
        //getting element of text area
        WebElement textArea = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/p[1]/input"));
        //declaring array which contains testcases and their probable answers
        String arr[] = new String[10];
        String ans[] = new String[10];
        arr[0] = "1";
        ans[0] = "The factorial of 1 is: 1";
        arr[1] = "\'1\'";
        ans[1] = "Please enter an integer";
        arr[2] = "-1";
        ans[2] = "Please enter a non negative integer";
        arr[3] = "1.1";
        ans[3] = "Please enter an integer";
        arr[4] = "a";
        ans[4] = "Please enter an integer";
        arr[5] = "5";
        ans[5] = "The factorial of 5 is: 120";
        arr[6] = "0";
        ans[6] = "The factorial of 0 is: 1";
        arr[7] = "171";
        ans[7] = "The factorial of 171 is: Infinity";
        //creating webElement to store the answe webelement
        WebElement answer = null;
        String ansComp;
        //sending keys
        for (int i = 0; i < 8; i++) {
            //sending Keys
            textArea.sendKeys(arr[i]);
            //clicking the Submit button
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/p[2]/button")).click();
            Thread.sleep(3000);
            //getting the webElemt which stores answer
            try {
                answer = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/p"));
            } catch (Exception e) {
                System.out.println("No output");
                continue;
            }
            //getting the string
            ansComp = answer.getText();
            //comparing to expected output
            if (!ansComp.equals(ans[i])) {
                System.out.println("entered " + arr[i] + "\nexpected " + ans[i] + "got: " + ansComp + "\n");
            } else {
                System.out.println("Passed");
            }
        }
    }
}