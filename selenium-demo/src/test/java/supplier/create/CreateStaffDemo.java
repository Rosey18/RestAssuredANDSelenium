package supplier.create;

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

public class CreateStaffDemo {

	private String fName = "me";
	private String lName = "not";
	private String email = "x63iv@wimsg.com"; // don't forget to change email
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Test
	public void addNewStuff() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String xpath = "//div[@class='col col-5']//label";
		driver.manage().window().maximize();

		try {

			driver.get("https://testseller.clevermart.kz/#/employees");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Добавить']"))).click();
			Thread.sleep(1000);

			List<WebElement> fields = driver.findElements(By.xpath(xpath));
			for (WebElement label : fields) {
				if (label.getText().equals("Имя")) {
					String id = label.getAttribute("for");
					driver.findElement(By.id(id));
					findAndSend(driver, fName, id);
				}
				if (label.getText().equals("Фамилия")) {
					String id = label.getAttribute("for");
					driver.findElement(By.id(id));
					findAndSend(driver, lName, id);
				}
				if (label.getText().equals("Электронная почта")) {
					String id = label.getAttribute("for");
					driver.findElement(By.id(id));
					findAndSend(driver, email, id);
				}
			}

			driver.findElement(By.xpath("//span[.='Сохранить']")).click();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void findAndSend(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(value);
	}

}
