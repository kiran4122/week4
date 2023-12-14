package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlesLeafTaps {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	//Enter user id and password	
		
	WebElement username =driver.findElement(By.id("username"));
	username.sendKeys("DemoCSR");
	driver.findElement(By.id("password")).sendKeys("crmsfa");
	driver.findElement(By.className("decorativeSubmit")).click();
	
	// Select CRMSFA link
	
	WebElement crmsfa = driver.findElement(By.linkText("CRM/SFA"));
	System.out.println(crmsfa.getText());
	driver.findElement(By.linkText("CRM/SFA")).click();
	
	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.linkText("Merge Contacts")).click();
	driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();
	//System.out.println("After Switching " +driver.getTitle());
	
	Set<String> windowHandle = driver.getWindowHandles();

		
		List<String> winHan=new ArrayList<String>(windowHandle);
		driver.switchTo().window(winHan.get(1));
		driver.findElement(By.xpath("(//div/a[@class='linktext'])[1]")).click();
		driver.switchTo().window(winHan.get(0));	
		Thread.sleep(2000);
		

		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		//System.out.println("After Switching " +driver.getTitle());
		Set<String> windowHandle1 = driver.getWindowHandles();
		List<String> winHan1=new ArrayList<String>(windowHandle1);
		driver.switchTo().window(winHan1.get(1));
		driver.findElement(By.xpath("(//div/a[@class='linktext'])[5]")).click();
		driver.switchTo().window(winHan1.get(0));	

	//	System.out.println("Parent Window " +driver.getTitle());
		
	driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		Thread.sleep(5000);
		
		String title = driver.getTitle();
		if(title.equals("View Contact | opentaps CRM")) {
			System.out.println("View Contact page is loaded");
		}
		else {
			System.out.println("Something went wrong");
		}
		
	}

	}


