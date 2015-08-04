package test;

import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.netdimen.config.Config;

public class PhantomJSTest {

	public static void testPhantomJS(){
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "./lib/phantomjs-1.9.7-windows/phantomjs-1.9.7-windows/phantomjs.exe");

		PhantomJSDriver driver = new  PhantomJSDriver(capabilities);
		String URL =  Config.getInstance().getProperty("loginURL");
		
		driver.get(URL);
		By by = By.id("UID");
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys("ndadmin");
		System.out.println("Done to fill in UID");
		
		by = By.id("PWD");
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys("123456");
		System.out.println("Done to fill in PWD");
		
		by = By.name("login");
		driver.findElement(by).click();
		System.out.println("Done to login");
		
		driver.close();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testPhantomJS();
	}

}
