package sokoeurnchhayacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sokoeurnchhayacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;	//Assign driver to local class
	
	public LandingPage(WebDriver driver) {		//this driver is from creation of LandingPage in StandAloneClass
		
		super(driver);	//super is used to pass driver from child class(landingPage) to parent class(Abstract Component)
		
		this.driver= driver;	//initialization driver;
		
		PageFactory.initElements(driver, this); 	//initialize driver to all desired elements
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//	WebElement password = driver.findElement(By.id("userPassword"));
//	WebElement submit = driver.findElement(By.id("login"));
	
	//PageFactory Desing Pattern
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
//	@FindBy(xpath="login")
//	WebElement submit1;
	
//	@FindBy(css="login")
//	WebElement submit2;
	
//	@FindBy(className="login")
//	WebElement submit3;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//Create action for WebElement
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
