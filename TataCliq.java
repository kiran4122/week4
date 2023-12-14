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
import org.openqa.selenium.support.ui.Select;

public class TataCliq {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.tatacliq.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//button[@id='wzrk-cancel']")).click();
		WebElement men = driver.findElement(By.xpath("//div[text()='Brands']"));
		
		Actions act=new Actions(driver);		
		act.moveToElement(men).perform();
		
		WebElement watch = driver.findElement(By.xpath("//div[text()='Watches & Accessories']"));
		Actions act2=new Actions(driver);		
		act2.moveToElement(watch).perform();
		
		 driver.findElement(By.xpath("//div[text()='Casio']")).click();
		 
		 WebElement dropdwn = driver.findElement(By.xpath("//select[@class='SelectBoxDesktop__hideSelect']"));
		 Select dd = new Select(dropdwn);
		dd.selectByVisibleText("New Arrivals");
		
		driver.findElement(By.xpath("(//div[@class='FilterDesktop__newFilName'])[1]")).click();
		Thread.sleep(5000);
		//List of all product prices
		List<WebElement> price = driver.findElements(By.xpath("//div[@class='ProductDescription__content']//h3"));
		for (int i = 0; i < price.size(); i++) {
			 System.out.println(price.get(i).getText());
			}

			
		 	String price1 = price.get(0).getText();
		 	int price2 = Integer.parseInt(price1.replaceAll("[^0-9]", ""));
		 	System.out.println("The first page price is : "+price2);

				
		Thread.sleep(5000);
		WebElement findElement = driver.findElement(By.xpath("(//div[@class='ProductDescription__content']//h3)[1]"));
		
		//js click next page
		driver.executeScript("arguments[0].click();", findElement);
		//window handling
		Set<String> windowHandle = driver.getWindowHandles();
		List<String> winHan=new ArrayList<String>(windowHandle);
		driver.switchTo().window(winHan.get(1));
		
		String text2 = driver.findElement(By.xpath("//h3[text()='MRP:  ₹11995']")).getText();
		
	//	System.out.println("second page price:"+text2);
		String replaceAll = text2.replaceAll("[^0-9]", "");
		int replaceAll2 = Integer.parseInt(replaceAll.replaceAll("[^0-9]", ""));
		System.out.println("The second page price is :"+replaceAll2);
		
		
		if(price2==replaceAll2) {
			System.out.println("The price is same in two pages");
		}
		
		else {
			System.out.println("The price is differnet in two pages");
		}
	
		
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();		
		 String text = driver.findElement(By.xpath("(//div/span[text()='1'])[1]")).getText();
		 System.out.println("Count of bag :" +text);
		//take screenshot
		File scource = driver.getScreenshotAs(OutputType.FILE);
		File destn=new File("./snap/page1.png");//setting the filepath
		FileUtils.copyFile(scource, destn);
		 System.out.println("Screenshot taken");
		 //close current window
		driver.close();
		//close parent
		driver.switchTo().window(winHan.get(0));
		driver.close();

	}
	
	

}
