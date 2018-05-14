package bitFury.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CandidatesPage {
	public WebDriver driver;

	public CandidatesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(className = "button-red")
	private WebElement btSelectCandidate;

	@FindBy(xpath = "//div[@ng-click='submitCandidate()']")
	private WebElement btSubmitCandidate;

	@FindBy(className = "list-option-description")
	private WebElement lbCandidateInfo;

	@FindBy(linkText = "Official candidate page")
	private WebElement hrOfficialSite;

	public void voteForCandidate() {
		btSelectCandidate.click();
		confirmVote();
	}

	private void confirmVote() {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(btSubmitCandidate));
			btSubmitCandidate.click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

	public void selectCandidate(String fullname) {
		try {
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + fullname + "']")));
			driver.findElement(By.xpath("//td[text()='" + fullname + "']")).click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public String getCandidateInfo() {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(lbCandidateInfo));
			return lbCandidateInfo.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getCandidatesOfficialSiteInfo(int length) {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(hrOfficialSite));
			hrOfficialSite.click();
			return _getCandidatesOfficialSiteInfo(length);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private String _getCandidatesOfficialSiteInfo(int length) {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.numberOfWindowsToBe(2));
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			CandidateWikiPage wPage = new CandidateWikiPage(driver);
			String offInfo = wPage.getFirstPagahraph().substring(0, length);
			driver.close();
			driver.switchTo().window(tabs.get(0));
			return offInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}