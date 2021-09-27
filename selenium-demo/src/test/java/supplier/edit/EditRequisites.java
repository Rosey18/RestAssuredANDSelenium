package supplier.edit;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EditRequisites {

	private String[] bank = { "Ситибанк Казахстан", "Шинхан Банк Казахстан", "ТП Банк Китая в Алматы", "Ситибанк Казахстан", "Отбасы банк",
			"Нурбанк", "Народный сберегательный банк Казахстана", "Исламский Банк \"Al-Hilal\"", "Заман-Банк", "Евразийский Банк",
			"ДО АО Банк ВТБ (Казахстан)" };
	private String kbe = "44";
	private String IBAN = "KZ000000000000000001";
	private String type = "не мой счет";
	private String[] currency = {"USD", "KZT", "RUR", "EUR"};

	String xpath = "//div[@class='v-card__text']//label";

	@Test
	public void editReq() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://testseller.clevermart.kz/#/requisites");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);

		Thread.sleep(1000);
		List<WebElement> li = driver.findElements(By.xpath("//tbody//tr//td[last()]//span//i"));
		li.get(0).click();
		Thread.sleep(1000);

		iterateThroughRequisites(driver, xpath);

		/*
		 * WebElement inputId = driver.findElement(By.xpath(
		 * "//div[@class='v-data-footer']//div[@role='button']")); String attr =
		 * inputId.getAttribute("aria-owns");
		 * 
		 * driver.findElement(By.xpath("(//div[@class='v-data-footer']//i)[1]")).click()
		 * ; WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[@id="+attr+"]")));
		 * driver.findElement(By.xpath(".//div[contains(string(), \"100\")]")).click();
		 */

	}

	public void iterateThroughRequisites(WebDriver driver, String xpath) throws InterruptedException {

		Random random = new Random();
		int randomize = random.nextInt(bank.length);
		String randomBank = bank[randomize];
		
		int mix = random.nextInt(currency.length);
		String randomCurrency = currency[mix];

		List<WebElement> fields = driver.findElements(By.xpath(xpath));
		for (WebElement label : fields) {
			if (label.getText().equals("Банк")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id)).click();
				arrowDownEnter(driver, randomBank, id);
			}
			if (label.getText().equals("КБЕ")) {
				String id = label.getAttribute("for");
				findAndSend(driver, kbe, id);
			}
			if (label.getText().equals("IBAN")) {
				String id = label.getAttribute("for");
				findAndSend(driver, IBAN, id);
			}
			if (label.getText().equals("Тип счета")) {
				String id = label.getAttribute("for");
				findAndSend(driver, type, id);
			}
			if (label.getText().equals("Валюта счета")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id)).click();
				arrowDownEnter(driver, randomCurrency, id);
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[.='Сохранить']")).click();

	}

	public void findAndSend(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.findElement(By.id(id)).sendKeys(value);
	}
	
	public void arrowDownEnter(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
	}

}
