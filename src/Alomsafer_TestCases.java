
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExcelReportListener.class)
public class Alomsafer_TestCases extends TestData {

	WebDriver driver = new ChromeDriver();
	String URL = "https://www.almosafer.com/en";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1)
	public void checkTheDefaultLanguageIsEnglish() {
		String websiteLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(websiteLanguage, expectedDefaultLanguage);
	}

	@Test(priority = 2)
	public void checkTheDefaultCurrency() {
		WebElement currencyButton = driver.findElement(By.cssSelector(".sc-hUfwpO.kAhsZG"));
		String currency = currencyButton.getText();

		Assert.assertEquals(currency, expectedCurrency);
	}

	@Test(priority = 3)
	public void checkContactNumber() {
		WebElement contactTag = driver.findElement(By.className("sc-cjHlYL"));
		String actualPhoneNumber = contactTag.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(actualPhoneNumber, expectedPhoneNumber);
	}

	@Test(priority = 4)
	public void checkQitafLogo() throws InterruptedException {
		WebElement saudiButton = driver.findElement(By.cssSelector(".cta__saudi"));
		saudiButton.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,9100)");
		Thread.sleep(2000);
		WebElement footer = driver.findElement(By.tagName("footer"));
		WebElement qitafLogo = footer.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR"));
		boolean isQitafLogoDisplayed = qitafLogo.isDisplayed();
		Assert.assertTrue(isQitafLogoDisplayed, "Qitaf logo is not displayed!");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(9100,0)");

	}

	@Test(priority = 5)
	public void checkHotelsTabNotDefault() {
		WebElement staysTab = driver.findElement(By.cssSelector("a[data-testid='Header__HotelsNavigationTab']"));
		String initialClass = staysTab.getDomAttribute("class");
		Assert.assertEquals(initialClass, expectedDefaultHotelsClass);
	}

	@Test(priority = 6)
	public void checkFlightsDepartureDate() {
		WebElement monthDateElement = driver.findElement(By.cssSelector(".sc-eSePXt.ljMnJa"));
		WebElement dayDateElement = driver.findElement(By.cssSelector(".sc-dXfzlN.iPVuSG"));

		String actualDepartureMonth = monthDateElement.getText();
		String actualDepartureDay = dayDateElement.getText();
		if (actualDepartureDay.startsWith("0")) {
			actualDepartureDay = actualDepartureDay.substring(1);
		}
		Assert.assertEquals(actualDepartureDay, expectedDepartureDay);
		Assert.assertEquals(actualDepartureMonth, expectedDepartureMonth);

	}

	@Test(priority = 8)
	public void checkFlightsReturnDate() throws InterruptedException {
		WebElement dateDiv = driver.findElement(By.cssSelector(".sc-bYnzgO.sc-hvvHee.aiGEY"));
		WebElement monthDateElement = dateDiv.findElement(By.cssSelector(".sc-eSePXt.ljMnJa"));
		WebElement dayDateElement = dateDiv.findElement(By.cssSelector(".sc-dXfzlN.iPVuSG"));

		String actualReturnMonth = monthDateElement.getText();
		String actualReturnDay = dayDateElement.getText();
		if (actualReturnDay.startsWith("0")) {
			actualReturnDay = actualReturnDay.substring(1);
		}

		Assert.assertEquals(actualReturnMonth, expectedReturnMonth);
		Assert.assertEquals(actualReturnDay, expectedReturnDay);

	}

	@Test(priority =8)
	public void chooseRandomLanguage() throws InterruptedException {
       //true->change language
		String actualLanguage="";
		if(switchLanguage) {
			WebElement languageSwitch = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));
			languageSwitch.click();
			Thread.sleep(2000);
			expectedLanguage="ar";
		    actualLanguage=driver.findElement(By.tagName("html")).getDomAttribute("lang");
			Assert.assertEquals(actualLanguage, expectedLanguage);
		}
		else {
			expectedLanguage="en";
		    actualLanguage=driver.findElement(By.tagName("html")).getDomAttribute("lang");
			Assert.assertEquals(actualLanguage, expectedLanguage);
		}
	}

	@Test(priority = 9)
	public void staysTab() throws InterruptedException {

		WebElement staysTab = driver.findElement(By.xpath("//a[@data-testid='Header__HotelsNavigationTab']"));
		staysTab.click();
		Thread.sleep(1000);
		
		String websiteLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		
		//assertion to check if website select stay(hotel) tab
		String actucalStaysTabTitle = driver.getTitle();
		System.out.println(actucalStaysTabTitle);
		if (websiteLanguage.equals("en")) {
			Assert.assertEquals(actucalStaysTabTitle, expectedStaysTabTitleInEnglish);
		}
		else if(websiteLanguage.equals("ar")) {
			Assert.assertEquals(actucalStaysTabTitle, expectedStaysTabTitleInArabic);
		}
		
	}
	
	@Test(priority = 10)
	public void stayLocation() throws InterruptedException {

		String websiteLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		System.out.println(websiteLanguage);
		WebElement inputSearchStayField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
		String actualSelectedStay = "";
		boolean sameStaySelecion = false;
		if (websiteLanguage.equals("en")) {
			inputSearchStayField.sendKeys(expectedCityInEnglish);
			Thread.sleep(1000);
			// inputSearchStayField.sendKeys(Keys.ENTER);
			inputSearchStayField.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
			String selectedValue = inputSearchStayField.getDomAttribute("value");
			actualSelectedStay = selectedValue;
			sameStaySelecion = actualSelectedStay.contains(expectedCityInEnglish);

		}

		else if (websiteLanguage.equals("ar")) {
			inputSearchStayField.sendKeys(expectedCityInArabic);
			Thread.sleep(1000);
			inputSearchStayField.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
			String selectedValue = inputSearchStayField.getDomAttribute("value");
			actualSelectedStay = selectedValue;
			sameStaySelecion = actualSelectedStay.contains(expectedCityInArabic);
		}

		Assert.assertTrue(sameStaySelecion);
	}
	@Test(priority = 11)
	public void selectRandomRoom_And_GuestOptions() throws InterruptedException {
		WebElement reservationSearchBox = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select reservationSelectList = new Select(reservationSearchBox);
		reservationSelectList.selectByValue(randomRoomReservationOption);

	}

	@Test(priority = 12)
	public void searchHotelsResultsPage() throws InterruptedException {
		WebElement searchHotelsButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchHotelsButton.click();

		try {
			Thread.sleep(6000);
			WebElement loadingBar = driver.findElement(By.cssSelector(
					"span.MuiLinearProgress-bar.MuiLinearProgress-barColorPrimary.MuiLinearProgress-bar1Determinate"));
		} 
		catch (NoSuchElementException e) {

			Assert.assertTrue(true, "Loading bar is still visible – page not fully loaded!");
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit(); // Closes the browser after tests
	}
}
