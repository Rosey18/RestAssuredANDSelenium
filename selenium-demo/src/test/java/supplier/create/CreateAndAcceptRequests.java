package supplier.create;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateAndAcceptRequests {

	LocalDate ld = LocalDate.now();
	int todaysDayOfMonth = ld.getDayOfMonth();

	Calendar cal = Calendar.getInstance();
	int lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);

	@Test
	public void createRequest() {

		List<BarcodeAndAmount> ba = new ArrayList<BarcodeAndAmount>();
		ba.add(new BarcodeAndAmount("2201304982520", "10"));
		ba.add(new BarcodeAndAmount("2771817524741", "10"));
		ba.add(new BarcodeAndAmount("2854217625536", "10"));
		ba.add(new BarcodeAndAmount("2746835680311", "10"));
		ba.add(new BarcodeAndAmount("2718301274669", "10"));
		ba.add(new BarcodeAndAmount("2386122733704", "10"));

		int all = ba.size();

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		try {

			driver.get("https://testseller.clevermart.kz/#/supplies");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Добавить']"))).click();

			driver.findElement(By.xpath("(//div[@class='col-8']//input)[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[.=" + todaysDayOfMonth + "]")).click();
			driver.findElement(By.xpath("//td[.=" + lastDayOfMonth + "]")).click();
			driver.findElement(By.xpath("(//div[@class='col-8']//input)[2]")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

			for (int i = 2; i <= all; i++) {
				driver.findElement(By.xpath("(//button[@type='button'])[" + i + "]")).click();
			}

			List<WebElement> barcodeInputs = driver.findElements(By.xpath("//div[@class='product']//input[@type!='hidden']"));
			for (int i = 0; i < barcodeInputs.size(); i += 2) {
				barcodeInputs.get(i).click();
				barcodeInputs.get(i).sendKeys(ba.get(i / 2).barcodes);
				Thread.sleep(3000);
				barcodeInputs.get(i).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
				barcodeInputs.get(i + 1).click();
				barcodeInputs.get(i + 1).sendKeys(ba.get(i / 2).amount);
			}

			driver.findElement(By.xpath("//span[.=' Отправить ']")).click();

			Thread.sleep(5000);
			String rowNum = driver.findElement(By.xpath("//div[@class='v-data-table__wrapper']/table/tbody/tr[" + 9 + "]/td[" + 1 + "]")).getText();
			System.out.println("Created postavka no: " + rowNum);

			driver.get("https://testcrm.clevermart.kz/#/wms/operations/supplier-deliveries");
			Thread.sleep(3000);

			boolean found = false;
			List<WebElement> elements = driver.findElements(By.xpath("//div[@class='v-data-table__wrapper']/table/tbody/tr/td"));
			while(!found) {
				if (driver.findElements(By.xpath("//tbody/tr[.//td/span[contains(text()," + rowNum + ")]]")).size() > 0) {
					System.out.println("displayed");
					driver.findElement(By.xpath("//tbody/tr[.//td/span[contains(text()," + rowNum + ")]]/td//button")).click();
					found = true;
					break;
				} else  {
					driver.findElement(By.xpath("//div[@class='v-data-footer__icons-after']//button")).click();
					Thread.sleep(1000);
				}
			}

			WebElement label = driver.findElement(By.xpath("//div[@class='v-text-field__slot']//label[contains(text(),'Выберите дату поставки')]"));
			String id = label.getAttribute("for");
			findAndSend(driver, id);

			Thread.sleep(1000);
			driver.findElement(By.xpath("//span[.='ПРИНЯТЬ']")).click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void findAndSend(WebDriver driver, String id) throws InterruptedException {
		driver.findElement(By.id(id)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[.=" + lastDayOfMonth + "]")).click();
	}

	class BarcodeAndAmount {

		String barcodes;
		String amount;

		BarcodeAndAmount(String barcodes, String amount) {
			this.barcodes = barcodes;
			this.amount = amount;

		}
	}

}
