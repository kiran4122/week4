package week4.day2;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertBuyTheValue {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://buythevalue.in/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("(//h3/a[@class='product-title'])[1]")).click();
		driver.findElement(By.xpath("//input[@name='wk_zipcode']")).sendKeys("560100");
		driver.findElement(By.xpath("//div[@class='wk_zipfinder_btn']/input")).click();
		driver.findElement(By.xpath("(//button/span[contains(text(),'Add to Cart')])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[text()='View Cart'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='checkout']")).click();
		
		Alert alert = driver.switchTo().alert();
	
		alert.accept();
		driver.close();
	}

}
