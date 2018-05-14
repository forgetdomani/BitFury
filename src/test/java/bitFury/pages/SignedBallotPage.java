package bitFury.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignedBallotPage {
	public WebDriver driver;

	public SignedBallotPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@ng-model='inputs.email']")
	private WebElement tbEmail;

	@FindBy(className = "button-green")
	private WebElement btSendEmail;

	public void submitBallot(String email) {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(tbEmail));
			tbEmail.sendKeys(email);
			driver.findElement(By.xpath("//span[text()='Ballot has been signed']")).click();
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btSendEmail);
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(btSendEmail));
			btSendEmail.click();

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}
}
