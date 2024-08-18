package sokoeurnchhayacademy;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("xyzstars@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Xyz12345");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		
		//In case of syncronization issue. use this way
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//Wait for aninmation icon while loading add to cart
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[class='ng-tns-c31-1 ng-star-inserted']"))));
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
	
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		
		driver.findElement(By.cssSelector(".subtotal button[class='btn btn-primary']")).click();
		
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@class,'ta-item')])[2]"))).click();
		
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		
		String confirmedMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		
		 Assert.assertTrue( confirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		Thread.sleep(Duration.ofSeconds(3));
		driver.quit();
	}

}
