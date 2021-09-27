package supplier.edit;

import java.time.Duration;
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

public class EditAccountAndCreateRequisites {

	private String organization = "ИП"; // это не работает, потому что данное поле просто drop-down list без ввода данных
	private String orgName = "Master Guardian";
	private String iinBin = "457845562300";
	private String city = "Алма-аты";
	private String address = "Аль-Фараби 33";

	private String[] bank = {"Ситибанк Казахстан", "Шинхан Банк Казахстан", "ТП Банк Китая в Алматы", "Ситибанк Казахстан", "Отбасы банк", "Нурбанк", 
			"Народный сберегательный банк Казахстана", "Исламский Банк \"Al-Hilal\"", "Заман-Банк", "Евразийский Банк", "ДО АО Банк ВТБ (Казахстан)"}; 
	private String kbe = "44";
	private String IBAN = "KZ000000000000000001";
	private String type = "не мой счет";
	private String currency = "USD";
	

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIinBin() {
		return iinBin;
	}

	public void setIinBin(String iinBin) {
		this.iinBin = iinBin;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getBank() {
		return bank;
	}

	public void setBank(String[] bank) {
		this.bank = bank;
	}

	public String getKbe() {
		return kbe;
	}

	public void setKbe(String kbe) {
		this.kbe = kbe;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	String xpath = "//div[@class='v-card__text']//label";

	@Test
	public void addReq() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://testseller.clevermart.kz/#/requisites");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Добавить']"))).click();
		Thread.sleep(2000);
		
		iterateThroughRequisites(driver, xpath);

	}

	@Test
	public void editProfile() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String xpath = "//div[@class='v-card__text']//label";

		try {
			driver.manage().window().maximize();
			
			driver.get("https://testseller.clevermart.kz/#/requisites");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Редактировать']"))).click();
			Thread.sleep(1000);

			iterateThroughElements(driver, xpath);
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void iterateThroughRequisites(WebDriver driver, String xpath) throws InterruptedException {
		
		Random random = new Random();
		int randomize = random.nextInt(bank.length);
		String randomBank = bank[randomize];

		List<WebElement> fields = driver.findElements(By.xpath(xpath));
		for (WebElement label : fields) {
			if (label.getText().equals("Банк")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id));
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
				arrowDownEnter(driver, currency, id);
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[.='Сохранить']")).click();

	}

	public void iterateThroughElements(WebDriver driver, String xpath) throws InterruptedException {

		List<WebElement> labels = driver.findElements(By.xpath(xpath));
		for (WebElement label : labels) {
			if (label.getText().equals("Организационно-правовая форма")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id));
				arrowDownEnter(driver, organization, id);
			}
			if (label.getText().equals("Наименование")) {
				String id = label.getAttribute("for");
				findAndSend(driver, orgName, id);
			}
			if (label.getText().equals("ИИН / БИН")) {
				String id = label.getAttribute("for");
				findAndSend(driver, iinBin, id);
			}
			if (label.getText().equals("Юр. адрес")) {
				String id = label.getAttribute("for");
				findAndSend(driver, city, id);
			}
			if (label.getText().equals("Факт. адрес")) {
				String id = label.getAttribute("for");
				findAndSend(driver, address, id);
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
