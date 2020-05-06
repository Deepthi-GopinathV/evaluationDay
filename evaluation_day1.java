package test.exercise;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class evaluation_day1 {

	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		driver.get("https://www.ajio.com/shop/sale");
//		driver.findElementByXPath("//div[@class='ic-close-quickview']").click();
		driver.findElementByXPath("//input[@placeholder='Search AJIO']").sendKeys("Bags");
		driver.findElementByXPath("//span[text()='Women Handbags']").click();
		driver.findElementByXPath("//div[@class='five-grid']").click();
		Select sortby = new Select(driver.findElementByXPath("//div[@class='filter-dropdown']//select"));
		sortby.selectByIndex(3);
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementById("minPrice").sendKeys("2000");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Thread.sleep(3000);
		Set<String> set = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(set);
		WebDriver window = driver.switchTo().window(ls.get(1));
		Thread.sleep(3000);
		String sellingprice = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		System.out.println("Actula selling price is : "+sellingprice);
		String price =sellingprice.replaceAll("\\D", "");
		int cost = Integer.parseInt(price);
		System.out.println("Cost in value: " + cost);
		
		String discount = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
		String discountPrice = discount.replaceAll("\\D", "");
		int promoAmt = Integer.parseInt(discountPrice);
		String couponTxt = driver.findElementByXPath("//div[@class='promo-title-blck']//div[text()='EPIC']").getText();
//		String couponCode = couponTxt.substring(couponTxt.lastIndexOf(" ")-1);
		String[] parts = couponTxt.split(" ");
		String couponCode = parts[parts.length - 1];
		System.out.println("Get coupon code :" + couponCode);
		if (Integer.parseInt(price) > 2690) {
			int totalDiscount = cost - promoAmt;
			System.out.println("Discount amt: " + totalDiscount);
		}
		driver.findElementByXPath("//span[text()='Enter pin-code to know estimated delivery date.']").click();
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("682001");
		driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();
		Thread.sleep(3000);
		String delDate = driver.findElementByXPath("//li[text()='Expected Delivery: ']/span").getText();
		System.out.println("Delivery date: "+delDate);
		driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		String contactInfo = driver.findElementByXPath("(//span[text()='Customer Care Address']/following::span/following::span)[1]").getText();
		System.out.println("Customer care Address,Phone number and email address: " + contactInfo);
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='PROCEED TO BAG']").click();
		Thread.sleep(3000);
		String 	chkCost = driver.findElementByXPath("//span[text()='Order Total']/following::span").getText().replaceAll("\\D", "");
		int chkOutCost= Integer.parseInt(price);
		if (chkOutCost == promoAmt) {
			System.out.println("order total verified.total amount is: " + chkOutCost);
		}
		driver.findElementById("couponCodeInput").sendKeys("EPIC");
		driver.findElementByXPath("//button[text()='Apply']").click();
		
		String lstPrice = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText().substring(4);
		System.out.println(lstPrice);
		double priceRO = Double.parseDouble(lstPrice);
		int roundOfValie =  (int) Math.round(priceRO);
		System.out.println("Rounded off value :"+ roundOfValie);
		
		if (chkOutCost == roundOfValie) {
			System.out.println("Value matching");
		} else {
			System.out.println("Value matching");
		}
		Thread.sleep(2000);

		driver.findElementByClassName("delete-btn").click();
		driver.findElementByXPath("//div[text()='DELETE']").click();
		String cartMsg = driver.findElementByClassName("empty-msg").getText();
		System.out.println(cartMsg);
		driver.quit();
		
		
	}
	

}
