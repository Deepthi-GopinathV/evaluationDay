package test.exercise;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Evaluation_Day2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		driver.get("https://azure.microsoft.com/en-in/");
		driver.findElementByXPath("//a[text()='Pricing']").click();
		driver.findElementByXPath("//a[@href='/en-in/pricing/calculator/']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='Containers']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//button[@title='Container Instances']//span[text()='Container Instances'])[2]").click();
		//Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='View']")));
		driver.findElementByXPath("//a[text()='View']").click();
		Select region = new Select(driver.findElementByXPath("(//select[@name='region'])[1]"));
		region.selectByVisibleText("South India");
		driver.findElementByXPath("//div[@class='wa-textNumber']//input[@aria-label='Seconds']").sendKeys((Keys.CONTROL + "a"));
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='wa-textNumber']//input[@aria-label='Seconds']").sendKeys("180000");
		Select memory = new Select(driver.findElementByXPath("(//select[@name='memory'])[1]"));
		memory.selectByVisibleText("4 GB");
		driver.findElementById("devtest-toggler").click();
		Thread.sleep(2000);
		Select currencyType = new Select(driver.findElementByXPath("//select[@class='select currency-dropdown']"));
		currencyType.selectByValue("INR");
		String cost = driver.findElementByXPath("(//span[text()='Monthly cost']/parent::div/span[@class='numeric']/span)[1]").getText();
		System.out.println("Estimated cost is :"+cost);
		driver.findElementByXPath("//button[text()='Export']").click();
		Thread.sleep(3000);
		File file = new File("C:\\Users\\Deepthi\\Downloads\\ExportedEstimate.xlsx");
		if(file.exists())
		{
			System.out.println("Successfully downloaded");
		}
		else
		{
			System.out.println("Error while downloading. Verify");
		}
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).click().build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='Add to estimate']")));
		action.moveToElement(driver.findElementByXPath("//button[text()='Add to estimate']")).click().build().perform();;
		Thread.sleep(3000);
		driver.findElementByXPath("//button[@class='lp_close lpc_maximized-header__close-button lpc_desktop lp_ctooltip']").click();
		Thread.sleep(2000);
		Select currSelect = new Select(driver.findElementByXPath("//select[@class='select currency-dropdown']"));
		currSelect.selectByValue("INR");
		Thread.sleep(2000);
//		toggle is already enabled
//		driver.findElementById("devtest-toggler").click(); 
//		Thread.sleep(3000); 
		File file1 = new File("C:\\Users\\Deepthi\\Downloads\\ExportedEstimate (1).xlsx");
		if(file1.exists())
		{
			System.out.println("Successfully downloaded");
		}
		else
		{
			System.out.println("Error while re-estimated download. Verify");
		}
		
	}

}
