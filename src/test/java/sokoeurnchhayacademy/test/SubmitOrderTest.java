package sokoeurnchhayacademy.test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import sokoeurnchhayacademy.TestComponents.BaseTest;
import sokoeurnchhayacademy.pageobjects.CartPage;
import sokoeurnchhayacademy.pageobjects.CheckoutPage;
import sokoeurnchhayacademy.pageobjects.ConfirmationPage;
import sokoeurnchhayacademy.pageobjects.LandingPage;
import sokoeurnchhayacademy.pageobjects.OrderPage;
import sokoeurnchhayacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test
	public void submitOrder() throws IOException, InterruptedException{
		ProductCatalogue productCatalogue = landingPage.loginApplication("xyzstars@gmail.com", "Xyz12345");
		
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(productName);
		
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
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

}
