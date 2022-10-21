import java.time.Duration;
import java.util.List;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Scenario3 {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;

	@BeforeTest
	public void f() {
		System.setProperty("webdriver.edge.driver", "C:/Users/bbdnet10169/Documents/Jars/msedgedriver.exe");

		EdgeOptions opt = new EdgeOptions();
		opt.setExperimentalOption("debuggerAddress", "localhost:9222");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test
	public void fieldsTest() {
		String title = "Only Testing";
		String date = "Tuesday, 28 January 2014";
		if (title.equals(driver.findElement(By.className("title")).getText()))
			System.out.println(driver.findElement(By.className("title")).getText());

		if (date.equals(driver.findElement(By.className("date-header")).getText()))
			System.out.println(driver.findElement(By.className("date-header")).getText());

		boolean fn = driver.findElement(By.name("fname")).isEnabled();
		if (fn) {
			System.out.println("Text box First name is enabled. ");
			driver.findElement(By.name("fname")).sendKeys("Dixit");

		} else {
			System.out.println("Text box First name is disabled.");
		}
		boolean ln = driver.findElement(By.name("lname")).isEnabled();
		if (ln) {
			System.out.println("Text box Last name is enabled. ");
			driver.findElement(By.name("lname")).sendKeys("Patel");

		} else {
			System.out.println("Text box last name is disabled.");
		}
		// driver.findElement(By.id("text3")).sendKeys("kjkg");
		driver.findElement(By.id("text4"));
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("document.getElementById('text4').value='xyz';");
		String s = (String) j.executeScript("return document.getElementById('text4').value");
		System.out.print("Value entered in hidden field: " + s);

	}

	@AfterMethod
	public void CheckboxRadioSelect() {
		driver.findElement(By.id("check2")).click();
		driver.findElement(By.id("check3")).click();
		driver.findElement(By.id("radio2")).click();
		driver.findElement(By.id("check3")).click();
	}

	@AfterMethod
	public void fileUploadAndSelectdropdown() {

		driver.findElement(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/form[1]/input[10]"))
				.sendKeys("C:/Users/bbdnet10169/Documents/test.txt");
		Select car = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"Carlist\"]"))));
		car.selectByValue("BMW Car");
	}

	@AfterMethod
	public void FromAndTotest() {
		Select country = new Select(driver.findElement((By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/form[2]/table/tbody/tr/td[1]/select"))));
		country.selectByValue("India");

		driver.findElement(By.xpath("//input[@value='->' and @type='button']")).click();
		country.selectByValue("Japan");

		driver.findElement(By.xpath("//input[@value='->' and @type='button']")).click();
		Select removeCountry = new Select(driver.findElement(
				By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/form[2]/table/tbody/tr/td[3]/select")));
		removeCountry.selectByValue("India");
		driver.findElement(By.xpath("//input[@value='<-' and @type='button']")).click();
	}

	@AfterMethod
	public void ConfirmationAlertPrompt() {
		driver.findElement(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/button[1]")).click();
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/input")).click();
		driver.switchTo().alert().accept();
		WebElement btn = driver.findElement(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/button[2]"));
		WebElement text = driver.findElement(By.cssSelector("#Example"));
		btn.click();
		driver.switchTo().alert().sendKeys("John");
		driver.switchTo().alert().accept();
		Assertions.assertNotEmpty("Hi John ! Are you all right?", text.getText());
		System.out.println(text.getText());
		//Testtable
		List<WebElement> rows = driver
				.findElements(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/table/tbody/tr[1]/td[1]"));
		int rowcount = rows.size();
		System.out.println("Total row is :" + rowcount);
		WebElement tdata = driver
				.findElement(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/table/tbody/tr[2]/td[3]"));
		System.out.println(tdata.getText());

		driver.findElement(By.id("submitButton")).click();
		String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"post-body-4292417847084983089\"]/div[1]/a")).sendKeys(clicklnk);
		String mailclik = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"Blog1\"]/div[1]/div/div/div/div[1]/div[3]/div[1]/div/a[1]"))
				.sendKeys(mailclik);
		// driver.navigate().forward();

	}

	@AfterMethod
	public void openWindowTest() {

		driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
		driver.findElement(By.xpath("//*[@id=\"Blog1\"]/div[1]/div/div/div/div[1]/div[3]/div[1]/div/a[2]")).click();
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");

		String tweeter = Keys.chord(Keys.CONTROL, Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"Blog1\"]/div[1]/div/div/div/div[1]/div[3]/div[1]/div/a[3]"))
				.sendKeys(tweeter); // driver.navigate().forward();

		// driver.navigate().to("https://only-testing-blog.blogspot.com/2014/01/textbox.html");
		driver.findElement(By.xpath("//*[@id=\"Blog1\"]/div[1]/div/div/div/div[1]/div[3]/div[1]/div/a[4]")).click();
		driver.switchTo().newWindow(WindowType.WINDOW);

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
