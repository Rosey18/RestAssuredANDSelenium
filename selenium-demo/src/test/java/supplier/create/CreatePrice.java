package supplier.create;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreatePrice {

	@Test
	public void newPrice() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		try {

			driver.get("https://testseller.clevermart.kz/#/price-processing/price-list");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Добавить']"))).click();
			Thread.sleep(2000);

			WebElement uploadPrice = driver.findElement(By.xpath("//input[@id='file-input']"));
			uploadPrice.sendKeys("C:\\Users\\Zhomart\\Downloads\\price.xls");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//span[.=' Сохранить ']")).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Да']"))).click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
