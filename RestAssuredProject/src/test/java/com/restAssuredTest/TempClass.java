package com.restAssuredTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TempClass {
	
	@Test
	public void test() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
		email.click();
		email.sendKeys("aripbaizhomart@gmail.com");
		driver.findElement(By.xpath("//span[.='Далее']")).click();
		
		String url = driver.getCurrentUrl();
		String particialCode = url.split("code=")[1];
		String code = particialCode.split("&scope")[0];
		
		System.out.println(url);
	}

}
