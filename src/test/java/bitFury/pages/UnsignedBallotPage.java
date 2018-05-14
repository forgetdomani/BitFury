package bitFury.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UnsignedBallotPage {
	public WebDriver driver;

	public UnsignedBallotPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@ng-click='signModal()']")
	private WebElement btSignBallot;

	@FindBy(xpath = "//div[@ng-click='submitSign()']")
	private WebElement btSubmitSign;

	public void signBallot(String pin2) {
		doSignBallot();
		confirmSignBallot(pin2);
	}

	private void doSignBallot() {
		try {
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Ballot Receipt']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btSignBallot);
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(btSignBallot));
			btSignBallot.click();

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	private void confirmSignBallot(String pin2) {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(btSubmitSign));

			for (char digit : pin2.toCharArray()) {
				driver.findElement(By.xpath("//div[text()='" + digit + "']")).click();
			}

			btSubmitSign.click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
