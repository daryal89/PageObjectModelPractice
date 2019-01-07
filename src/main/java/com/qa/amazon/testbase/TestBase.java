package com.qa.amazon.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBase {

	public WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setUp() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\creat\\eclipse-workspace\\PageObjectModel\\src\\main\\java\\com\\qa\\amazon\\config\\config.properties");
		prop.load(fis);

		if (prop.getProperty("browser").equalsIgnoreCase("chorme")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\creat\\eclipse-workspace\\PageObjectModel\\src\\main\\java\\com\\qa\\amazon\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\creat\\eclipse-workspace\\PageObjectModel\\src\\main\\java\\com\\qa\\amazon\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
			// } else {
			// System.setProperty("webdriver.ie.driver",
			// "C:\\Users\\creat\\eclipse-workspace\\PageObjectModel\\src\\main\\java\\com\\qa\\amazon\\resources\\iedriver.exe");
			// driver = new InternetExplorerDriver();

		}

		driver.get(prop.getProperty("url"));
		System.out.println("The title of the page is: " + driver.getTitle());
		//
		// System.out.println(prop.getProperty("url"));
		// System.out.println(prop.getProperty("username"));
		// System.out.println(prop.getProperty("browser"));

	}

	@Test(priority = 1)
	public void login() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@value='Log In']")).click();

		System.out.println("Test ran successful");
	}

	@Test(priority = 0)
	public void register() {
		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("fname"));
		driver.findElement(By.name("lastname")).sendKeys(prop.getProperty("lname"));
		driver.findElement(By.name("reg_email__")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.name("reg_passwd__")).sendKeys(prop.getProperty("pw"));

		Select s1 = new Select(driver.findElement(By.name("birthday_month")));
		s1.selectByIndex(3);

		Select s2 = new Select(driver.findElement(By.name("birthday_day")));
		s2.selectByValue("5");

		Select s3 = new Select(driver.findElement(By.name("birthday_year")));
		s3.selectByVisibleText("1992");

		driver.findElement(By.xpath("//input[@type='radio' and @value='1']")).click();

		driver.findElement(By.xpath("//button[@name='websubmit']")).click();

		System.out.println("registration passed");

	}

	@AfterMethod()
	public void tearDown() {
		driver.close();
	}
}
