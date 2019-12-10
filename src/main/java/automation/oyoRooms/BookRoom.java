package automation.oyoRooms;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookRoom{
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		ConfigFileReader configFile = new ConfigFileReader();
		String city = configFile.getCity();
		System.setProperty("webdriver.chrome.driver", System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + configFile.getDriverPath());
		driver = new ChromeDriver();
		driver.get(configFile.getApplicationUrl());
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='autoComplete__home']")).sendKeys(city);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='oyo-row oyo-row--no-spacing']/div[2]//span[@class='d-text16 geoSuggestionsList__locationName' and contains(text(), 'Kolkata')]")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[@class='datePickerDesktop__checkInOutText'][1]")).click();		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='DateRangePicker__Month'][2]//td[3]//span[text()= '1']")).click();		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='DateRangePicker__Month'][2]//td[5]//span[text()= '3']")).click();	
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(text(), 'Search')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='oyo-cell--12-col oyo-cell--8-col-tablet oyo-cell--4-col-phone'][1]//span[contains(text(), 'Book Now')]")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for(String childWindow: allWindows) {
			
			if(!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
				break;
			}
		}
		driver.findElement(By.xpath("//div[@class='c-qln3k6'][1]//input[@class='textInput__input']")).sendKeys("Test");
		driver.findElement(By.xpath("//div[@class='c-qln3k6'][2]//input[@class='textInput__input']")).sendKeys("OyoRooms");		
		driver.findElement(By.xpath("//input[@class='textTelInput__input textTelInput__input--noLabel']")).sendKeys("7012570125");
		if(driver.findElement(By.xpath("//button[@class='c-c2mqcb']")).isEnabled()) {
			System.out.println("The test is successful");
		}
		else {
			System.out.println("The test has failed");
		}
		driver.quit();
	}
}
