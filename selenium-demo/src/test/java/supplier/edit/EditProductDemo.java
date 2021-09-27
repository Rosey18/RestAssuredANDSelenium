package supplier.edit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class EditProductDemo {

	private String nomenclatureCode;
	private String[] nCode;
	private String nomCode;
	private String sizes;
	private String descriptions;

	private String[] categoryList = { "Носки и колготки", "Колготки и чулки", "Тапочки", "Трусы" };
	private String[] countryList = { "Албания", "Алжир", "Барбадос", "Куба", "Сингапур", "Науру" };
	private String[] colorList = { "Серый", "Темно-серый", "Лиловый", "Нюдовый", "Розово-лавандовый", "Снег" };

	@Test
	public void editProduct() throws InterruptedException, FileNotFoundException {

		try {

			FileInputStream fis = new FileInputStream(new File("C:\\Users\\Zhomart\\Downloads\\Milavitsa.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFRow sheet = wb.getSheetAt(0).getRow(70);

			int[] arr = { 2, 3, 5, 6, 8, 10, 11, 12, 13 };

			for (int index : arr) {
				Cell cell = sheet.getCell(index);
				// System.out.println(cell);
			}
			nomenclatureCode = sheet.getCell(arr[5]).getStringCellValue().trim();
			nCode = nomenclatureCode.split("\\s+");
			nomCode = String.join("", nCode[0]);
			sizes = sheet.getCell(arr[8]).getStringCellValue().trim();
			descriptions = sheet.getCell(arr[6]).getStringCellValue().trim();

			String xpath = "//div[@class='v-input__control']//label";

			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();

			driver.get("https://testseller.clevermart.kz/#/products");

			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("gaukhar@cm");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1", Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.xpath("//tbody//tr[last()]")).click();
			iterateThroughElements(driver, xpath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iterateThroughElements(WebDriver driver, String xpath) throws InterruptedException {

		Random random = new Random();
		int randomize = random.nextInt(categoryList.length);
		String randomCategory = categoryList[randomize];

		int mix = random.nextInt(countryList.length);
		String randomCountry = countryList[mix];

		int shake = random.nextInt(colorList.length);
		String randomColor = colorList[shake];

		Thread.sleep(1000);
		// iterate first step
		List<WebElement> labels = driver.findElements(By.xpath(xpath));
		for (WebElement label : labels) {
			if (label.getText().equals("Подкатегория")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id)).click();
				clearField(driver, randomCategory, id);
			}
			if (label.getText().equals("Страна производства")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id)).click();
				clearField(driver, randomCountry, id);
			}

		}
		driver.findElement(By.xpath("//span[.=' Далее ']")).click();
		Thread.sleep(1000);

		// iterate second step
		List<WebElement> fields = driver.findElements(By.xpath(xpath));
		for (WebElement label : fields) {
			if (label.getText().equals("Основной цвет")) {
				String id = label.getAttribute("for");
				driver.findElement(By.id(id)).click();
				findAndSend(driver, randomColor, id);
			}
			if (label.getText().equals("Размер")) {
				String id = label.getAttribute("for");
				findAndSend(driver, sizes, id);
			}
		}

		driver.findElement(By.xpath("//span[.='Сгенерировать']")).click();
		driver.findElement(By.xpath("//ul/li[last()]//span")).click();

		iterateThroughElementsOneMoreTime(driver, xpath);

	}

	public void iterateThroughElementsOneMoreTime(WebDriver driver, String xpath) throws InterruptedException {

		Random random = new Random();
		int shake = random.nextInt(colorList.length);
		String randomColor = colorList[shake];
		
		Thread.sleep(1000);

		// iterate second step
		List<WebElement> fields = driver.findElements(By.xpath(xpath));
		for (WebElement label : fields.subList( 5, fields.size())) {
			if (label.getText().equals("Основной цвет")) {
				String id = label.getAttribute("for");
				findAndSend(driver, randomColor, id);
			}
			if (label.getText().equals("Артикул товара")) {
				String id = label.getAttribute("for");
				findAndSend(driver, nomCode, id);
			}
			if (label.getText().equals("Размер")) {
				String id = label.getAttribute("for");
				findAndSend(driver, sizes, id);
			}

		}

		driver.findElement(By.xpath("(//span[.='Сгенерировать'])[2]")).click();
		driver.findElement(By.xpath("(//input[@id='file-input'])[2]")).sendKeys("C:\\Users\\Zhomart\\Downloads\\nike.jpg");
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.=' Сохранить ']"))).click();
		driver.findElement(By.xpath("//span[.=' Далее ']")).click();
		Thread.sleep(1000);

		// third step
		String priceId = driver.findElement(By.xpath("//label[.='Розница']")).getAttribute("for");
		driver.findElement(By.id(priceId)).sendKeys("4508");
		driver.findElement(By.xpath("//span[.='Установить цену']")).click();
		driver.findElement(By.xpath("//span[.=' Далее ']")).click();

		// fourth step
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(@class,'ql-editor')][contains(.,'')]")).sendKeys(descriptions);
		String keyWordid = driver.findElement(By.xpath("//label[.='Ключевые слова']")).getAttribute("for");
		driver.findElement(By.id(keyWordid)).sendKeys("ырывррыврв", Keys.ENTER);
		driver.findElement(By.xpath("//span[.=' Создать ']")).click();
	}

	public void findAndSend(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
	}
	
	public void clearField(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		driver.findElement(By.id(id)).sendKeys(value);
	}

}
