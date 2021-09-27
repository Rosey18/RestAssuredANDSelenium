package supplier.create;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FindRequest {

	String result = "64";

	Calendar cal = Calendar.getInstance();
	int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);

	@Test
	public void findEl() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://testcrm.clevermart.kz/#/wms/operations/return");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);
		Thread.sleep(2000);

		itterateRemains(driver);

	}

	public void itterateRemains(WebDriver driver) throws InterruptedException {
		boolean found = false;
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']/table/tbody/tr/td"));
		while (!found) {
			if (driver.findElements(By.xpath("//tbody/tr[.//td/span[contains(text()," + result + ")]]")).size() > 0) {
				System.out.println("displayed");
				driver.findElement(By.xpath("//tbody/tr[.//td/span[contains(text()," + result + ")]]/td//button")).click();
				found = true;
				break;
			} else {
				driver.findElement(By.xpath("//div[@class='v-data-footer__icons-after']//button")).click();
				Thread.sleep(1000);
			}
		}
		driver.findElement(By.xpath("//span[.='œ–»Õﬂ“‹']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[.=" + lastDayOfMonth + "]")).click();
		driver.findElement(By.xpath("//span[.='ŒÍ']")).click();

	}

}
