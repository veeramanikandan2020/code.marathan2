package code.marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

/*
 * Credentials:
hari.radhakrishnan@qeagle.com
Leaf@1234
Testcase-1: Administrator Certifications
==============================
1. Launch Salesforce application https://login.salesforce.com/
2. Login with username as "hari.radhakrishnan@qeagle.com" 
    and password as "Leaf@1234"
3. Click on Learn More link in Mobile Publisher
4. Click confirm on Confirm redirect
5. Click Learning 
6. And mouse hover on Learning On Trailhead
7. Clilck on Salesforce Certifications
8. Click on first resulting Ceritificate
9. verify - Administrator Overview page
10.Print the Certifications available for Administrator Certifications (List)
11.Take a Snapshot of Loaded Ceritificate
 */

public class SalesForceCertification {

	public static void main(String[] args) throws IOException {
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//1. Launch Salesforce application https://login.salesforce.com/
		
		driver.get("https://login.salesforce.com/");
		
		//2. Login with username as "hari.radhakrishnan@qeagle.com" 
	    //and password as "Leaf@1234"
		
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com");
		
		driver.findElement(By.id("password")).sendKeys("Leaf@1234");
		
		driver.findElement(By.id("Login")).click();
		
		//3. Click on Learn More link in Mobile Publisher
		
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		Set<String> setWindowHandles = driver.getWindowHandles();
		
		List<String> listWindowHandles = new ArrayList<String>(setWindowHandles);
		
		driver.switchTo().window(listWindowHandles.get(1));
		
		//4. Click confirm on Confirm redirect
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//5. Click Learning 
		
		Shadow shadow = new Shadow(driver);
		
		shadow.findElementByXPath("//span[text()='Learning']").click();
		
		//6. And mouse hover on Learning On Trailhead
		
		Actions action = new Actions(driver);
		
		WebElement target = shadow.findElementByXPath("//span[text()='Learning on Trailhead']");
		
		action.moveToElement(target).perform();
		
		//7. Clilck on Salesforce Certifications
		
		WebElement sales = shadow.findElementByXPath("//a[text()='Salesforce Certification']");
		
		action.moveToElement(sales).perform();	
		
		//8. Click on first resulting Ceritificate
		
		sales.click();
		
		//9. verify - Administrator Overview page
		
		String admin = driver.getTitle();
		
		if(admin.contains("Administrator"))
		{
			System.out.println("Title contains Administrator string in it and Title of page is"+admin);
		}
		else
		{
			System.out.println("Title doesn't contains Administrator string in it and Title of page is"+admin);
		}
		
		//10.Print the Certifications available for Administrator Certifications (List)
		
		List<WebElement> certificates = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		
		System.out.println("Total number of Administrator Certifications are : "+certificates.size());
		
		for (WebElement certificate : certificates) {
			
			System.out.println(certificate.getText());
		}
		
		WebElement adminCertificate = driver.findElement(By.xpath("//a[text()='Marketing Cloud Administrator']"));
		
		action.moveToElement(adminCertificate).perform();
		
		//11.Take a Snapshot of Loaded Ceritificate
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		
		File destination = new File("./SalesForceCertification/cerificates.png");
		
		FileUtils.copyFile(src, destination);
		
	}

}
