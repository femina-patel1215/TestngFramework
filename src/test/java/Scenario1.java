import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Scenario1 {
	WebDriver driver;
	Actions actions;
	WebDriverWait wait;

	@BeforeTest
	public void f() {
		System.setProperty("webdriver.edge.driver", "C:/Users/bbdnet10169/Documents/Jars/msedgedriver.exe");
		driver = new EdgeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		actions = new Actions(driver);
		driver.get("http://automationpractice.com/index.php");
		System.out.println("open browser");
	}

	@Test
	public void placeOrder() {
		driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"ul_layered_id_attribute_group_3\"]/li[3]/label/a")).click();
		driver.findElement(By.xpath("//*[@id=\"subcategories\"]/ul/li[2]/div[1]/a/img")).click();
		driver.findElement(By.xpath("//*[@id=\"subcategories\"]/ul/li[2]/div[1]/a")).click();
		WebElement li = driver.findElement(By.className("ajax_block_product"));
		actions.moveToElement(li).perform();
		WebElement qw = driver.findElement(By.className("quick-view"));
		if (qw.isDisplayed()) {
			qw.click();
		}

		WebDriver driver1 = driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));

		boolean elementDisplay = driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[2]/h1")).isDisplayed();
		if (elementDisplay) {
			if (driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[2]/h1")).getText().equals("Printed Dress")
					&& driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[3]/form/div/div/div/p/span"))
							.getText().equals("$50.99")
					&& driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[2]/p")).getText()
							.equals("Model demo_4")
					&& driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[2]/p[2]")).getText()
							.equals("Condition New")
					&& driver.findElement(By.xpath("//*[@id=\"product\"]/div/div/div[2]/div/div/p")).getText().equals(
							"Printed evening dress with straight sleeves with black thin waist belt and ruffled linings."))
				System.out.println("Pass");
			else
				System.out.println("Fail");

		} else {
			System.out.println("Element is not display");
		}
		WebElement sizeSelect = driver.findElement(By.id("group_1"));
		Select size = new Select(sizeSelect);
		size.selectByValue("2");
		driver.findElement(By.id("add_to_cart")).click();

		// Verify Sucessfully added to cart message and click on Proceed to checkout
		if (wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")))
				.getText().equals("Product successfully added to your shopping cart"))
			System.out.println("Message Verified");
		else
			System.out.println("Fail Incorrect message");
		driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")).click();
		driver.navigate().forward();
		driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"email\"]")))
				.sendKeys("femina@bbd.co.za");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"passwd\"]"))).sendKeys("F12345");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]")).click();

		driver.navigate().forward();
		Select address = new Select(wait
				.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id=\"id_address_delivery\"]")))));
		address.selectByValue("754691");

		driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
		driver.navigate().forward();
//select checkbox
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cgv\"]"))).click();
		driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();
		driver.navigate().forward();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")))
				.click();

		driver.navigate().forward();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"cart_navigation\"]/button")))
				.click();

		driver.navigate().forward();
		String str = "Your order on My Store is complete.Please send us a bank wire with\r\n" + "- Amount $52.99\r\n"
				+ "- Name of account owner Pradeep Macharla\r\n" + "- Include these details xyz\r\n"
				+ "- Bank name RTP\r\n"
				+ "- Do not forget to insert your order reference UWRANUXOB in the subject of your bank wire.\r\n"
				+ "An email has been sent with this information.\r\n"
				+ "Your order will be sent as soon as we receive payment.\r\n"
				+ "If you have questions, comments or concerns, please contact our expert customer support team. .\r\n"
				+ "\r\n" + "N	";
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Your order on My Store')]"));
		if (list.size() > 0) {
			System.out.println("Text: " + str + " is present. ");
		} else {
			System.out.println("Text: " + str + " is not present. ");
		}

	}
}
