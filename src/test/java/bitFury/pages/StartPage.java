package bitFury.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage {
	public WebDriver driver;

	public StartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(className = "button-red")
	private WebElement btGoVote;

	public void goVote() {
		try {
			new WebDriverWait(driver, 60)
			.until(ExpectedConditions.elementToBeClickable(btGoVote));
			btGoVote.click();
		}
		catch  (Exception e) {
			e.printStackTrace();
			return;
		}		
	}	
}
