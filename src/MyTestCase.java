import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCase {
	
	
	WebDriver driver = new ChromeDriver();	
	String website1 = "https://automationteststore.com/";
			
	//String website2 = "https://www.google.com";	
		
	String[] FirstNames = {"ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };
		
	String[] LastNames = {"Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };
		
	Random rand = new Random();	

	String GlobalUserName = "";

	String GlobalUserNameForTheLogin = ""; 


		
	@BeforeTest	
	public void SetUp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	driver.manage().window().maximize();	
	driver.get(website1);	
	//driver.navigate().to(website2);	
	//driver.navigate().back();	
	//driver.navigate().forward();

	//System.out.println(driver.getCurrentUrl());
	//System.out.println(driver.getTitle());


	}	
		
		
		
	@Test(priority=1)	
	public void Signup() throws InterruptedException {
		
	int RndomIndexForTheFirstName = rand.nextInt(FirstNames.length);
	int RandomIndexForTheLastName = rand.nextInt(LastNames.length);

	String UserFirstName= FirstNames[RndomIndexForTheFirstName];
	String UserLastName= LastNames[RandomIndexForTheLastName];
	int RandomNumberInput = rand.nextInt(45621);
	String domainname = "@gmail.com";
	String email = (UserFirstName+UserLastName+RandomNumberInput+domainname);
	String Password = "sosolove96@s";
		

	driver.findElement(By.linkText("Login or register")).click();

	WebElement SignUPButton = driver.findElement(By.xpath("//button[@title='Continue']"));
	SignUPButton.click();

	WebElement UserFirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
	UserFirstNameInput.sendKeys(UserFirstName);	

	GlobalUserName = UserFirstName;	

	WebElement UserlastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
	UserlastNameInput.sendKeys(UserLastName);	
		
	WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
	EmailInput.sendKeys(email);

	WebElement AddressInput = driver.findElement(By.id("AccountFrm_address_1"));
	AddressInput.sendKeys("Ammancity- tabarbour");

	WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
	CityInput.sendKeys("CapitalCity");

	Thread.sleep(2000);
	WebElement CountryInput= driver.findElement(By.id("AccountFrm_country_id"));
	Select selector2 = new Select(CountryInput);
	int randomcountry = rand.nextInt(1,240); 
	selector2.selectByIndex(randomcountry);
	Thread.sleep(2000);

	WebElement RegionInput = driver.findElement(By.id("AccountFrm_zone_id"));
	Select selector = new Select(RegionInput);
	int randomstate = rand.nextInt(1,6);
	selector.selectByIndex(randomstate);



	WebElement postalcodeInput = driver.findElement(By.id("AccountFrm_postcode"));
	postalcodeInput.sendKeys("13310");

	Thread.sleep(2000);

	WebElement LoginInput = driver.findElement(By.id("AccountFrm_loginname"));
	String UserNameLogin =UserFirstName+UserLastName+RandomNumberInput;

	LoginInput.sendKeys(UserNameLogin);
	GlobalUserNameForTheLogin = UserNameLogin;
	Thread.sleep(2000);

	WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
	PasswordInput.sendKeys(Password);

	WebElement confirmInput = driver.findElement(By.id("AccountFrm_confirm"));
	confirmInput.sendKeys(Password);

	WebElement CheckInput = driver.findElement(By.id("AccountFrm_agree"));
	CheckInput.click();
	Thread.sleep(2000);
	WebElement ContinueinInput = driver.findElement(By.xpath("//button[@title='Continue']"));
	ContinueinInput.click();
		
	}	
		
	@Test(priority=2 )	
	public void Logout() throws InterruptedException {
		
	Actions action = new Actions(driver);
	WebElement WelcomebackElement = driver.findElement(By.partialLinkText("back"));
	action.moveToElement(WelcomebackElement).perform();
		Thread.sleep(2000);
	WebElement LogoffButton = driver.findElement(By.partialLinkText("Logoff"));
	LogoffButton.click();

	}

	@Test(priority=3)
	public void Login() throws InterruptedException {
		Thread.sleep(2000);
	driver.findElement(By.linkText("Login or register")).click();  

	WebElement LoginNameinput = driver.findElement(By.id("loginFrm_loginname"));
	LoginNameinput.sendKeys(GlobalUserNameForTheLogin);

	String Password = "sosolove96@s";
	Thread.sleep(2000);
	WebElement PasswordInput = driver.findElement(By.id("loginFrm_password"));
		
	PasswordInput.sendKeys(Password);	

	WebElement LoginButton = driver.findElement(By.xpath("//button[@title='Login']"));  
	LoginButton.click();


	}



			@Test(priority = 4)

			public void AddItemToThecart() throws InterruptedException {

				String[] URLForTheItems = { "https://automationteststore.com/index.php?rt=product/category&path=68",
						"https://automationteststore.com/index.php?rt=product/category&path=36",
						"https://automationteststore.com/index.php?rt=product/category&path=43",
						"https://automationteststore.com/index.php?rt=product/category&path=49",
						"https://automationteststore.com/index.php?rt=product/category&path=58",
						"https://automationteststore.com/index.php?rt=product/category&path=52",
						"https://automationteststore.com/index.php?rt=product/category&path=65" };

				int randomIndex = rand.nextInt(URLForTheItems.length);
				driver.get(URLForTheItems[randomIndex]);

				
				WebElement ListOfITem = driver.findElement(By.cssSelector(".thumbnails.row"));

			
				
				int totalNumberOfItems = ListOfITem.findElements(By.tagName("li")).size();
				
				
				int RandomIdexForTheItem = rand.nextInt(totalNumberOfItems);

				
				Thread.sleep(3000);

				
				ListOfITem.findElements(By.tagName("li")).get(RandomIdexForTheItem).click();

		
				
				WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
				
				int numberOfPRoducts = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
				
				int randomIndexForTheproduct = rand.nextInt(numberOfPRoducts);

		
				
				Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomIndexForTheproduct).click();
				Thread.sleep(5000);

				
				WebElement ULList = driver.findElement(By.className("productpagecart"));

				
				int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();

				
				if (LiItem > 0) {
					driver.get(website1);

					System.out.println("sorry the item out of the stock ");
					String ExpectedResult = "https://automationteststore.com/";
					String ActualResult = driver.getCurrentUrl();
					Assert.assertEquals(ActualResult, ExpectedResult, "no");

				} else {

					driver.findElement(By.className("cart")).click();
					;
					Thread.sleep(2000);
					String ActualResult = driver.findElement(By.className("heading1")).getText();
					String ExpectedResult = "Shopping Cart";

					Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
					boolean ExpectedValueForCheckOut = true;
					boolean ActualValueForCheckOut = driver.findElement(By.id("cart_checkout1")).isDisplayed();
					Assert.assertEquals(ActualValueForCheckOut, ExpectedValueForCheckOut, "NO");
				}
			}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


