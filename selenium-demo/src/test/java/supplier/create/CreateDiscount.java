package supplier.create;

import java.time.LocalDate;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateDiscount {

	LocalDate ld = LocalDate.now();
	int todaysDayOfMonth = ld.getDayOfMonth();

	Calendar cal = Calendar.getInstance();
	int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);

	@Test
	public void newDiscount() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		try {

			driver.get("https://testseller.clevermart.kz/#/price-processing/discount-list");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);
			Thread.sleep(1000);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Добавить']"))).click();
			Thread.sleep(1000);

			WebElement uploadPrice = driver.findElement(By.xpath("//input[@id='file-input']"));
			uploadPrice.sendKeys("C:\\Users\\Zhomart\\Downloads\\discountPercentage.xls");

			driver.findElement(By.xpath("(//div[@class='col col-2']//input)[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[.=" + todaysDayOfMonth + "]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='col col-2']//input)[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[.=" + lastDayOfMonth + "]")).click(); // не кликается
			Thread.sleep(1000);

			driver.findElement(By.xpath("//span[.=' Сохранить ']")).click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
