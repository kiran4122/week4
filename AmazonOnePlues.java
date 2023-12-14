package week4.day2;

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
import org.openqa.selenium.interactions.Actions;

public class AmazonOnePlues {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		
		Thread.sleep(3000);
		
		String text = driver.findElement(By.xpath("(//span[@class='a-price'])[1]")).getText();
		
		text= text.concat(".00");
		System.out.println("New Phone Price is :"+text);
		
		String text1 = driver.findElement(By.xpath("(//span[@class='a-size-base s-underline-text'])[1]")).getText();
		System.out.println("Number of ratings:"+text1);
		
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> winhan1 = new ArrayList<String>(windowhandles);
		driver.switchTo().window(winhan1.get(1));
		
		Thread.sleep(5000);
		
		WebElement phone = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
		
		File Source = phone.getScreenshotAs(OutputType.FILE);
		File Destn = new File("./snap/phone1.png");
		FileUtils.copyFile(Source, Destn);
		
		WebElement mouse1 = driver.findElement(By.xpath("(//input[@name='submit.add-to-cart'])[2]"));
		Actions cl = new Actions(driver);
		cl.moveToElement(mouse1).perform();
	
		driver.findElement(By.xpath("(//input[@name='submit.add-to-cart'])[2]")).click();
		
	
		
		String text3 = driver.findElement(By.xpath("(//span[text()='₹61,999.00'])[5]")).getText();
		System.out.println("Subcart Total :"+text3);
		
		if(text.equals(text3)) {
			System.out.println("Price is same");
		}
			else {
				System.out.println("The price is different");
			}
		
		driver.quit();
		}
		;
	}


