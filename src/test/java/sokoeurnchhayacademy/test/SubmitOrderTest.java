package sokoeurnchhayacademy.test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import sokoeurnchhayacademy.TestComponents.BaseTest;
import sokoeurnchhayacademy.TestComponents.IRetryAnalyzer_Demo;
import sokoeurnchhayacademy.pageobjects.CartPage;
import sokoeurnchhayacademy.pageobjects.CheckoutPage;
import sokoeurnchhayacademy.pageobjects.ConfirmationPage;
import sokoeurnchhayacademy.pageobjects.LandingPage;
import sokoeurnchhayacademy.pageobjects.OrderPage;
import sokoeurnchhayacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups="Purchase", retryAnalyzer=IRetryAnalyzer_Demo.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry("india");
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmedMessage = confirmationPage.getConfirmationMessage();
		
		 Assert.assertTrue( confirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("xyzstars@gmail.com", "Xyz12345");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		ordersPage.VerifyOrderDisplay(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "xyzstars@gmail.com");
//		map.put("password", "Xyz12345");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "xyzstars@gmail.com");
//		map1.put("password", "Xyz12345");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\sokoeurnchhayacademy\\data\\PurchaseOrder.json");
		
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
