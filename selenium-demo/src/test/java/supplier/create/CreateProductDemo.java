package supplier.create;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

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

public class CreateProductDemo {

	private String categoryName;
	private String brandName;
	private String countryFrom;
	private String equipments;
	private String nomenclatureCode;
	private String[] nCode;
	private String nomCode;
	private String colorName;
	private String sizes;
	private String barcode;
	private String descriptions;

	@Test
	public void createProduct() throws IOException {

		String xpath = "//div[@class='v-input__control']//label";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zhomart\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// можно ли укзатать переменную в путь файлу, к примеру ${barcode}
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\Zhomart\\Downloads\\Milavitsa.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFRow sheet = wb.getSheetAt(0).getRow(78);

		int[] arr = { 2, 3, 5, 6, 8, 10, 11, 12, 13 };

		for (int index : arr) {
			Cell cell = sheet.getCell(index);
			// System.out.println(cell);
		}
		categoryName = sheet.getCell(arr[0]).getStringCellValue().trim();
		brandName = sheet.getCell(arr[2]).getStringCellValue().trim();
		countryFrom = sheet.getCell(arr[3]).getStringCellValue().trim();
		equipments = sheet.getCell(arr[4]).getStringCellValue().trim();

		nomenclatureCode = sheet.getCell(arr[5]).getStringCellValue().trim();
		nCode = nomenclatureCode.split("\\s+");
		nomCode = String.join("", nCode[0]);

		colorName = sheet.getCell(arr[7]).getStringCellValue().trim();
		sizes = sheet.getCell(arr[8]).getStringCellValue().trim();
		barcode = sheet.getCell(arr[1]).getStringCellValue().trim();
		descriptions = sheet.getCell(arr[6]).getStringCellValue().trim();

		try {

			driver.get("https://testseller.clevermart.kz/#/products");

			WebElement email = driver.findElement(By.xpath("//input[@id='username']"));
			email.sendKeys("gaukhar@cm");
			WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
			password.sendKeys("1", Keys.ENTER);

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Добавить']"))).click();
			Thread.sleep(1000);
			Thread.sleep(1000);

			iterateThroughElements(driver, xpath);

		} catch (Exception e) {
			e.printStackTrace();
		}
//			  finally { driver.quit(); }

	}

	public void iterateThroughElements(WebDriver driver, String xpath) throws InterruptedException {

		// iterate first step
		List<WebElement> labels = driver.findElements(By.xpath(xpath));
		for (WebElement label : labels) {
			if (label.getText().equals("Подкатегория")) {
				String id = label.getAttribute("for");
				findAndSend(driver, categoryName, id);
			}
			if (label.getText().equals("Бренд")) {
				String id = label.getAttribute("for");
				findAndSend(driver, brandName, id);
			}
			if (label.getText().equals("Страна производства")) {
				String id = label.getAttribute("for");
				findAndSend(driver, countryFrom, id);
			}
			if (label.getText().equals("Артикул поставщика")) {
				String id = label.getAttribute("for");
				findAndSend(driver, "12", id);
			}
			if (label.getText().equals("Комплектация")) {
				String id = label.getAttribute("for");
				findAndSend(driver, equipments, id);
			}

		}
		driver.findElement(By.xpath("//span[.=' Далее ']")).click();
		Thread.sleep(1000);

		// iterate second step
		List<WebElement> fields = driver.findElements(By.xpath(xpath));
		for (WebElement label : fields) {
			if (label.getText().equals("Основной цвет")) {
				String id = label.getAttribute("for");
				findAndSend(driver, colorName, id);
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

		driver.findElement(By.xpath("//span[.='Сгенерировать']")).click();
		driver.findElement(By.xpath("//input[@id='file-input']")).sendKeys("C:\\Users\\Zhomart\\Downloads\\nike.jpg");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[.=' Сохранить ']")).click();
		driver.findElement(By.xpath("//span[.=' Далее ']")).click();
		Thread.sleep(1000);

		// third step
		String priceId = driver.findElement(By.xpath("//label[.='Розница']")).getAttribute("for");
		driver.findElement(By.id(priceId)).sendKeys("4508");
		driver.findElement(By.xpath("//span[.='Установить цену']")).click();
		driver.findElement(By.xpath("//span[.=' Далее ']")).click();

		// fourth step
		driver.findElement(By.xpath("//div[contains(@class,'ql-editor')][contains(.,'')]")).sendKeys(descriptions);
		String keyWordid = driver.findElement(By.xpath("//label[.='Ключевые слова']")).getAttribute("for");
		driver.findElement(By.id(keyWordid)).sendKeys("ырывррыврв", Keys.ENTER);
		driver.findElement(By.xpath("//span[.=' Создать ']")).click();

	}

	public void findAndSend(WebDriver driver, String value, String id) {
		driver.findElement(By.id(id)).sendKeys(value, Keys.ARROW_DOWN, Keys.ENTER);
	}

	/*
	 * public void pushButton(WebDriver driver) {
	 * 
	 * List<WebElement> spans = driver.findElements(By.tagName("span")); for
	 * (WebElement span : spans) { if(span.getText().equals("ДАЛЕЕ")) {
	 * span.click(); } } }
	 */

}
