package Assignment.GoogleSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import graphql.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class SearchGoogle {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");

		// Instantiate a ChromeDriver class.
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		// Maximize the browser
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://www.google.com/");
	}

	@Test
	public void SearchinGoogle() {
		// find the search edit box on the google page
		WebElement p = driver.findElement(By.name("q"));

		// enter text with sendKeys() then apply submit()
		p.sendKeys("Appium");
		p.sendKeys(Keys.ENTER);

		driver.findElement(By.xpath("//a[@href='http://appium.io/']")).click();

		// click Accept button on cookie pop-up
		new WebDriverWait(driver, 20).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='md-button md-button--primary' and text()='Accept']")))
				.click();

		// get text from appium website
		String actualString = driver.findElement(By.className("md-content")).getText();
		System.out.println("actualString ===" + actualString + "===end===");

		// Check actual result and expected result
		Assert.assertTrue(actualString.contains("Welcome to the Appium documentation!"));
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
