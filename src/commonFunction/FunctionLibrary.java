package commonFunction;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean verifyLogin(String username,String password)
{
	driver.get(config.getProperty("Url"));
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.cssSelector(config.getProperty("ObjUser"))).sendKeys(username);
	driver.findElement(By.cssSelector(config.getProperty("ObjPass"))).sendKeys(password);
	driver.findElement(By.cssSelector(config.getProperty("ObjLoginBtn"))).sendKeys(Keys.ENTER);
	String expected ="dashboard";
	String actual =driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("Login Success::"+expected+"     "+actual,true);
		return true;
	}
	else
	{
		//capture error message
		String erormessage = driver.findElement(By.cssSelector(config.getProperty("ObjErrormessage"))).getText();
		Reporter.log(erormessage+"   "+expected+"    "+actual,true);
		return false;
	}
}
}