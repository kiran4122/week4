package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev220793.service-now.com/");	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("shXJjX5N-!5r");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(10000);
		
		Shadow dom = new Shadow(driver);
		dom.setImplicitWait(10);
		dom.findElementByXPath("//div[text()='All']").click();
		dom.findElementByXPath("//span[contains(text(),'Service Catalog')]").click();
		 WebElement eleFrame = dom.findElementByXPath(("//iframe[@title='Main Content']"));
		 driver.switchTo().frame(eleFrame);
		 driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();
	 driver.findElement(By.xpath("//h2/strong[contains(text(),'13 pro')]")).click();
	
	 driver.findElement(By.xpath("//label[contains(text(),'Yes')]")).click();
	 driver.findElement(By.xpath("//input[@class='cat_item_option sc-content-pad form-control']")).sendKeys("99");
	 
	 
	 WebElement findElement = driver.findElement(By.xpath("//select[@name='IO:ff1f478e9747011021983d1e6253af68']"));
	 
	 Select dropdown = new Select(findElement);
	 
	 dropdown.selectByValue("unlimited");
	 
	 driver.findElement(By.xpath("//label[contains(text(),'Sierra')]")).click();
	 driver.findElement(By.xpath("//label[contains(text(),'512 GB [add $300.00]')]")).click();
	 driver.findElement(By.xpath("//button[@id='oi_order_now_button']")).click();
	 
	 String text = driver.findElement(By.xpath("//span[contains(text(),'Thank you, your request has been submitted')]")).getText();
	 System.out.println(text);
	 
	 if (text.equals("Thank you, your request has been submitted")) {
		 System.out.println("Order Placed successfully");
	 }
	 else {
		System.out.println("Order not placed");
	}
	 
	 String text2 = driver.findElement(By.xpath("//a[@id='requesturl']")).getText();
	 System.out.println("Request Number"+text2);
	 
	 File source = driver.getScreenshotAs(OutputType.FILE);
	 File dest = new File("./snap/iphone.png");
	 FileUtils.copyFile(source, dest);
	 
	 driver.switchTo().defaultContent();
	 driver.close();
	 
			}
	}
