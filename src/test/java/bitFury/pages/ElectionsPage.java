package bitFury.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElectionsPage {
	public WebDriver driver;

	public ElectionsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(className = "button-red")
	private WebElement btSelectElections;

	public void chooseElections(String elections) {
		try {
			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + elections + "']")));
			driver.findElement(By.xpath("//td[text()='" + elections + "']")).click();
			btSelectElections.click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
