package bitFury.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bitFury.testData.BallotReceipt;

public class VoteSentPage {
	public WebDriver driver;

	public VoteSentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public BallotReceipt getBallotReceipt() {
		BallotReceipt retVal = new BallotReceipt();
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.className("code-box")));
			List<WebElement> tBoxes = driver.findElements(By.className("code-box"));
			retVal.setReceiptMemo(tBoxes.get(0).getText());
			retVal.setReceiptHash(tBoxes.get(1).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
