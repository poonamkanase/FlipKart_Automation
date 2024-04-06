package demo;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {

    ChromeDriver driver;
    
    @BeforeSuite
    public void init()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    @Test
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("http://www.flipkart.com");

        // WebElement closeLoginPopup = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
        // if(closeLoginPopup.isDisplayed())
        // {
        //     closeLoginPopup.click();
        // }

        WebElement searchButton = driver.findElement(By.xpath("//input[@name ='q']"));
        searchButton.sendKeys("Washing Machine");
        Thread.sleep(3000);
        driver.findElement(By.className("_2iLD__")).click();

        WebElement popularityButton = driver.findElement(By.xpath("//div[text()='Popularity']"));
        popularityButton.click();

        Thread.sleep(5000);

        List<WebElement> items = driver.findElements(By.xpath("//div[@data-id]"));
        int size = items.size();
        System.out.println(size);
        int count = 0;
        
        for(WebElement ele : items)
        {
            WebElement rate = ele.findElement(By.xpath(".//div[@class ='_3LWZlK']"));
            double rating = Double.parseDouble(rate.getText());
            //System.out.println(rate.getText());
            if(rating <= 4)
            {
                count++;
            }
        }
        System.out.println(count);
        
        System.out.println("end Test case: testCase01");
    }

    @Test
    public  void testCase02() throws InterruptedException{

        System.out.println("Start Test case: testCase02");
        driver.get("http://www.flipkart.com"); 

        WebElement closeLoginPopup = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
        if(closeLoginPopup.isDisplayed())
        {
            closeLoginPopup.click();
        }

        Thread.sleep(3000);

        WebElement searchButton = driver.findElement(By.xpath("//input[@name ='q']"));
        searchButton.sendKeys("iPhone");
        Thread.sleep(3000);
        driver.findElement(By.className("_2iLD__")).click();

        Thread.sleep(5000);

        List<WebElement> items = driver.findElements(By.xpath("//div[@data-id]"));
        int size = items.size();
        
        System.out.println(size);
        int count = 0;

        for(WebElement item : items)
        {
            String title = item.findElement(By.xpath(".//div[@class= '_4rR01T']")).getText();
            WebElement dis = item.findElement(By.xpath(".//div[@class ='_3Ay6Sb']/span"));
            String per = dis.getText();
            per = per.substring(0, per.indexOf('%'));
            double percent = Double.parseDouble(per);
            //System.out.println(per);
            if(percent > 10)
            {
                System.out.println(title);
                System.out.println(percent);
            }

        }
    

    }

    @Test
    public  void testCase03() throws InterruptedException{

        System.out.println("Start Test case: testCase02");
        driver.get("http://www.flipkart.com"); 

        // WebElement closeLoginPopup = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
        // if(closeLoginPopup.isDisplayed())
        // {
        //     closeLoginPopup.click();
        // }

        Thread.sleep(3000);

        WebElement searchButton = driver.findElement(By.xpath("//input[@name ='q']"));
        searchButton.sendKeys("Coffee Mug");
        Thread.sleep(3000);
        driver.findElement(By.className("_2iLD__")).click();

        Thread.sleep(5000);

        WebElement fourStarandAbove = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div/div/section[5]/div[2]/div/div[1]/div/label/div[1]"));
        fourStarandAbove.click();

        Thread.sleep(3000);

        List<WebElement> productTitles = driver.findElements(By.className("s1Q9rs"));
        System.out.println(productTitles.size());

        List<WebElement> productImg = driver.findElements(By.xpath("//img[@class = '_396cs4']"));
        System.out.println(productImg.size());

        List<WebElement> revCount = driver.findElements(By.xpath("//span[@class = '_2_R_DZ']"));
        System.out.println(revCount.size());

        String[][] products = new String[productTitles.size()][3];

        // Store product information in the array
        for (int i = 0; i < productTitles.size(); i++) {
            products[i][0] = productTitles.get(i).getText(); // Title
            products[i][1] = productImg.get(i).getAttribute("src"); // Image URL
            products[i][2] = revCount.get(i).getText().replaceAll("[^0-9]", ""); // Review count
        }

        // Sort products array based on review count in descending order
        Arrays.sort(products, (a, b) -> Integer.parseInt(b[2]) - Integer.parseInt(a[2]));

        // Print the title and image URL of the top 5 products with the highest number of reviews
        for (int i = 0; i < 5; i++) {
            System.out.println("Title: " + products[i][0]);
            System.out.println("Image URL: " + products[i][1]);
            System.out.println();
        }

        Thread.sleep(5000);

        System.out.println("end Test case: testCase03");


    }


}

