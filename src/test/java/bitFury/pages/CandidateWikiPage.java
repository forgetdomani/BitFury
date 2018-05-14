package bitFury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CandidateWikiPage {
	public WebDriver driver;

	public CandidateWikiPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='mw-parser-output']//p")
	private WebElement pFirst;

	public String getFirstPagahraph() {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(pFirst));
			String info = pFirst.getText();
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
