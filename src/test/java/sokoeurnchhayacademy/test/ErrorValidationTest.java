package sokoeurnchhayacademy.test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import sokoeurnchhayacademy.TestComponents.BaseTest;
import sokoeurnchhayacademy.TestComponents.IRetryAnalyzer_Demo;
import sokoeurnchhayacademy.pageobjects.CartPage;
import sokoeurnchhayacademy.pageobjects.CheckoutPage;
import sokoeurnchhayacademy.pageobjects.ConfirmationPage;
import sokoeurnchhayacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		landingPage.loginApplication("xyzstars@gmail.com", "Xyz123");
	
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test(retryAnalyzer=IRetryAnalyzer_Demo.class)
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
		

		ProductCatalogue productCatalogue = landingPage.loginApplication("xyzstars123@gmail.com", "Xyz12345");
		
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		
		Assert.assertFalse(match);
		
	}
	
	

}
