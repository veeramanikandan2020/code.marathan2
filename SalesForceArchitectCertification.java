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
 * Testcase-2: Architect Certifications
==========================
1. Launch Salesforce application https://login.salesforce.com/
2. Login with Provided Credentials
3. Click on Learn More link in Mobile Publisher
4. Click  On Learning
5. And mouse hover on Learning On Trailhead
6. Click on Salesforce Certifications
7. Choose Your Role as Salesforce Architect
8. Get the Text(Summary) of Salesforce Architect 
9. Get the List of Salesforce Architect Certifications Available
10.Click on Application Architect 
11.Get the List of Certifications available
12.Take a Snapshot of Loaded Ceritificate
 */

public class SalesForceArchitectCertification {

	public static void main(String[] args) throws IOException {
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		
		//1. Launch Salesforce application https://login.salesforce.com/
			
		driver.get("https://login.salesforce.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//2. Login with Provided Credentials
		
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com");
		
		driver.findElement(By.id("password")).sendKeys("Leaf@1234");
		
		driver.findElement(By.id("Login")).click();
		
		//3. Click on Learn More link in Mobile Publisher
		
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		Set<String> setWindowHandles = driver.getWindowHandles();
		
		List<String> listWindowHandles = new ArrayList<String>(setWindowHandles);
		
		driver.switchTo().window(listWindowHandles.get(1));
		
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//4. Click  On Learning
		
		Shadow shadow = new Shadow(driver);
		
		shadow.findElementByXPath("//span[text()='Learning']").click();
		
		//5. And mouse hover on Learning On Trailhead
		
		Actions action = new Actions(driver);
		
		WebElement target = shadow.findElementByXPath("//span[text()='Learning on Trailhead']");
		
		action.moveToElement(target).perform();
		
		//6. Click on Salesforce Certifications
		
		WebElement sales = shadow.findElementByXPath("//a[text()='Salesforce Certification']");
		
		action.moveToElement(sales).perform();		
		
		sales.click();
		
		//7. Choose Your Role as Salesforce Architect
		
		driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']")).click();
		
		//8. Get the Text(Summary) of Salesforce Architect
		
		String SalesforceArchitect = driver.findElement(By.xpath("//div[contains(@class,'cert-site_text slds-text-align')]")).getText();
		
		System.out.println(SalesforceArchitect);
		
		WebElement salesforceCredential = driver.findElement(By.xpath("//h1[text()='Salesforce Architect Credentials']"));
		
		action.moveToElement(salesforceCredential).perform();
		
		//9. Get the List of Salesforce Architect Certifications Available
		
		List<WebElement> listSalesforceArchitect = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		
		System.out.println("Total Salesforce Architect Certifications Available are :"+listSalesforceArchitect.size());
		
		for (WebElement webElement : listSalesforceArchitect) {
			
			System.out.println(webElement.getText());
			
		}
		
		//10.Click on Application Architect 
		
		driver.findElement(By.xpath("//a[text()='Application Architect']")).click();
		
		List<WebElement> listApplicationArchitect = driver.findElements(By.xpath("//div[@class='credentials-card_title']"));
		
		System.out.println("Total Number of Application Architect certificates  :"+listApplicationArchitect.size());
		
		//11.Get the List of Certifications available
		
		for (WebElement certificate : listApplicationArchitect) {
			
			System.out.println(certificate.getText());
		}
		
		  WebElement platformAppBuilder = driver.findElement(By.xpath("//a[text()='Platform App Builder']"));
		  
		  action.moveToElement(platformAppBuilder).perform();
		
		//12.Take a Snapshot of Loaded Ceritificate
		File src = driver.getScreenshotAs(OutputType.FILE);
		
		File destination = new File("./SalesForceArchitectCertification/certificates.png");
		
		FileUtils.copyFile(src, destination);
		
	}

}
