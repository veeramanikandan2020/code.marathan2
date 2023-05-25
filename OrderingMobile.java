package code.marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

/*
 * Testcase:3 Ordering mobile
===================
Instance URL:https://dev70206.service-now.com
Username: admin
Password: g=Sl+bJvA99D
1. Launch ServiceNow application
2. Login with valid credentials username as admin and password 
3. Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
4. Click on  mobiles
5. Select Apple iphone6s
6. Update color field to Rose gold and storage field to 128GB
7. Select  Order now option
8. Verify order is placed and copy the request number"
9.Take a snapshot of Order status page.
 */

public class OrderingMobile {

	public static void main(String[] args) throws IOException {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(options);

		// 1. Launch ServiceNow application

		driver.get("https://dev70206.service-now.com");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 2. Login with valid credentials username as admin and password

		driver.findElement(By.id("user_name")).sendKeys("admin");

		driver.findElement(By.id("user_password")).sendKeys("g=Sl+bJvA99D");

		driver.findElement(By.id("sysverb_login")).click();

		// 3. Click-All Enter Service catalog in filter navigator and press enter or
		// Click the ServiceCatalog

		Shadow shadow = new Shadow(driver);

		shadow.setImplicitWait(10);

		shadow.findElementByXPath("//div[@id='all']").click();

		shadow.setImplicitWait(10);

		shadow.findElementByXPath("//span[text()='Service Catalog']").click();

		shadow.setImplicitWait(2);

		WebElement frame = shadow.findElementByXPath("//iframe[@id='gsft_main']");

		// 4. Click on mobiles

		driver.switchTo().frame(frame);

		driver.findElement(By.partialLinkText("Mobiles")).click();

		// 5. Select Apple iphone6s

		driver.findElement(By.xpath("//strong[text()='Apple iPhone 6s']")).click();

		// 6. Update color field to Rose gold and storage field to 128GB

		WebElement SDD = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[2]"));

		Select sec = new Select(SDD);

		sec.selectByVisibleText("Rose Gold");

		WebElement storage = driver.findElement(By.xpath("(//select[@class='form-control cat_item_option '])[3]"));

		Select stg = new Select(storage);

		stg.selectByValue("onetwentyeight");

		// 7. Select Order now option

		driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();

		// 8. Verify order is placed and copy the request number"

		String requestNo = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();

		System.out.println("Apple Iphone6S Order Request No.:" + requestNo);

		if (requestNo.contains("REQ"))
			System.out.println("Apple Iphone6S Order Placed successfully");
		else
			System.out.println("Apple Iphone6S Order is not placed successfully");

		// 9.Take a snapshot of Order status page.

		File src = driver.getScreenshotAs(OutputType.FILE);

		File destination = new File("./iphone/iphone6s.png");

		FileUtils.copyFile(src, destination);
	}

}
