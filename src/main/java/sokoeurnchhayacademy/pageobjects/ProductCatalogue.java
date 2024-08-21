package sokoeurnchhayacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sokoeurnchhayacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;	//Assign driver to local class
	
	public ProductCatalogue(WebDriver driver) {		//this driver is from creation of LandingPage in StandAloneClass
		
		super(driver); //super is used to push child driver(ProductCalalogue) to parant class(AbstractComponent)
				
		this.driver= driver; //initialization driver;
		
		PageFactory.initElements(driver, this); 	//initialize driver to all desired elements
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory Desing Pattern
	@FindBy(css="userEmail")
	List<WebElement> products;
	
	By productBy = By.cssSelector(".mb-3");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	By addToCart = By.cssSelector(".btn.w-10.rounded");
	By toastMessage = By.cssSelector("#toast-container");
	
	@FindBy(css="[class='ng-tns-c31-1 ng-star-inserted']")
	WebElement spinner;
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		
		waitForElementToAppear(toastMessage);
		
		waitForElementToDisappear(spinner);
		
		
	}

}
