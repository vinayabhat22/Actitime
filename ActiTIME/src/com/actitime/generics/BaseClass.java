package com.actitime.generics;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.LoginPage;

public class BaseClass {
	static {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
	}
	public static WebDriver driver;
	public FileLib f=new FileLib();
	@BeforeTest
	public void openBrowser()
	{
		System.out.println("opening browser");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@AfterTest
	public void closeBrowser()
	{
		System.out.println("close browser");
		driver.close();
	}
	@BeforeMethod
	public void login() throws IOException
	{
		System.out.println("login");
		String url = f.getPropertyData("url");
		String un = f.getPropertyData("un");
		String pw = f.getPropertyData("pw");
		driver.get(url);
		LoginPage l=new LoginPage(driver);
		l.setLogin(un, pw);
	}
	@AfterMethod
	public void logout()
	{
		System.out.println("logout");
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.clickLogout();
	}

}
