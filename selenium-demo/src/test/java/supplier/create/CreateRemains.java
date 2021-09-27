package supplier.create;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateRemains {

	String result;

	Calendar cal = Calendar.getInstance();
	int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);

	@Test
	public void newRemain() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://testseller.clevermart.kz/#/leftovers");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);
		Thread.sleep(2000);

		createRemains(driver);

		driver.get("https://testcrm.clevermart.kz/#/wms/operations/return");
		Thread.sleep(3000);

		itterateRemains(driver);

	}

	public void createRemains(WebDriver driver) throws InterruptedException {

		List<String> barcodes = new ArrayList<String>();
		barcodes.add("2349454996254");
		// barcodes.add("2995733234649");
		// barcodes.add("2771817524741");

		for (int i = 0; i < barcodes.size(); i++) {
			driver.findElement(By.xpath("//form[@class='search-form']//button")).click();
			Thread.sleep(1000);
			WebElement web = driver.findElement(By.xpath("//form[@class='search-form search-active']//input"));
			web.click();
			web.sendKeys(barcodes.get(i));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//th//div[@class='v-input--selection-controls__ripple']")).click();
			Thread.sleep(1000);
			web.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//form[@class='search-form']//button")).click();
			Thread.sleep(2000);

		}

		driver.findElement(By.xpath("//span[.=' Оформить возврат ']")).click();
		Thread.sleep(2000);

		List<WebElement> inputs = driver.findElements(By.xpath("//div[@class='refund-modal__content']//tbody//td[last()]//input"));
		for (WebElement input : inputs) {
			input.click();
			Thread.sleep(2000);
			input.sendKeys("2");

		}

		driver.findElement(By.xpath("//span[.=' Создать ']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.=' Да ']"))).click();
		Thread.sleep(1000);
		WebElement text = driver.findElement(By.xpath("//div[@class='success-modal__content']//span"));
		String t = text.getText();
		String r = t.split("№")[1];
		result = r.split(" принята в обработку")[0];
		System.out.println("Created remains: " + result);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[.=' OK ']")).click();
		Thread.sleep(2000);
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
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[.='ПРИНЯТЬ']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[.=" + lastDayOfMonth + "]")).click();
		driver.findElement(By.xpath("//span[.='Ок']")).click();

	}

}
