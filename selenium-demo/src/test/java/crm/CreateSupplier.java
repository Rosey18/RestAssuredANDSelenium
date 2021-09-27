package crm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CreateSupplier {
	
	private String orgName = "";
	private String iinBin = "";
	private String city = "";
	private String address = "";
	private String headName = "";
	private String phoneNumber = "";
	private String email = "";
	
	
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

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	String xpath = "//div[@class='v-input__control']//label";

	@Test
	public void addSupplier() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		try {
			
			driver.get("https://testcrm.clevermart.kz/#/crm/counterparties/suppliers");

			WebElement email = driver.findElement(By.xpath("//input[@id='username']"));
			email.sendKeys("gaukhar@cm");

			WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
			password.sendKeys("1", Keys.ENTER);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.=' Поставщик ']"))).click();
			Thread.sleep(1000);
			
			iterrateThroughElements(driver, xpath);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void iterrateThroughElements(WebDriver driver, String xpath2) {
		
		List<WebElement> labels = driver.findElements(By.xpath(xpath));
		for (WebElement label : labels) {
			if (label.getText().equals("Организационно-правовая форма")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id)).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
			}
			if (label.getText().equals("Наименование")) {
				String id = label.getAttribute("for");
				findAndSend(driver, orgName, id);
			}
			if (label.getText().equals("ИИН/БИН")) {
				String id = label.getAttribute("for");
				findAndSend(driver, iinBin, id);
			}
			if (label.getText().equals("Юр.адрес")) {
				String id = label.getAttribute("for");
				findAndSend(driver, city, id);
			}
			if (label.getText().equals("Факт.адрес")) {
				String id = label.getAttribute("for");
				findAndSend(driver, address, id);
			}
			if (label.getText().equals("Контактное лицо")) {
				String id = label.getAttribute("for");
				findAndSend(driver, headName, id);
			}
			if (label.getText().equals("Телефон")) {
				String id = label.getAttribute("for");
				findAndSend(driver, phoneNumber, id);
			}
			if (label.getText().equals("Email")) {
				String id = label.getAttribute("for");
				findAndSend(driver, email, id);
			}
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='СОХРАНИТЬ']"))).click();
	}
	
	public void findAndSend(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
	}

}
