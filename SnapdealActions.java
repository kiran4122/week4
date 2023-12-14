package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SnapdealActions {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com/");	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		//Mens shoes
		driver.findElement(By.xpath("(//a/span[@class='catText'])[1]")).click();
		//sports shoes
		driver.findElement(By.xpath("(//a/span[text()='Sports Shoes'])[1]")).click();
		// count of sport shoes printed
		String text = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("Count of shoes : "+text);
		//Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		//sort from low to high
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("//ul/li[@data-sorttype='plth']")).click();
		Thread.sleep(3000);	
		//Printing the price
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		for (int i = 0; i < price.size(); i++) {
		 System.out.println(price.get(i).getText());
		}
		
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("500");
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("700");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
	///	driver.findElement(By.xpath("//span[@class='filter-color-tile White&Blue ']/following-sibling::a")).click();
		Thread.sleep(3000);		
		WebElement shoes = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
	
		Actions act=new Actions(driver);
		act.moveToElement(shoes).perform();
		

		
		//Quick view Click
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		
		WebElement item1 = driver.findElement(By.xpath("(//img[@class='cloudzoom'])[1]"));
		
		File src = item1.getScreenshotAs(OutputType.FILE);
		File des=new File("./snap/shoes.png");//setting the filepath
		FileUtils.copyFile(src, des);
		
		// Print text on child window :Amount
	String text3 = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		
	System.out.println("final amount:" +text3);
	// Print text on child window :discount
String text4 = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		
		System.out.println("discount:"+text4);
		//Close the small window
		driver.findElement(By.xpath("//div/i[@class='sd-icon sd-icon-delete-sign']")).click();
		//close the window
		driver.close();
		}

}
