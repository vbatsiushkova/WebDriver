package page;

import helpers.Waiter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageTest extends AbstractUIPage {

	private static final String PATH = "http://staging.remedly.com/user-login";
	private static final String URL = "http://staging.remedly.com";

	@FindBy(name = "email")
	WebElement emailInput;

	@FindBy(name = "password")
	WebElement passwordInput;

	@FindBy(xpath = ".//button[contains(., 'LOG IN')]")
	WebElement loginBtn;

	public void visit() {
		visitUiPage(PATH);
	}

	public void setEmailInput(String emailValue) {
		Waiter.wait(emailInput);
		emailInput.clear();
		emailInput.sendKeys(emailValue);
	}

	public void setPasswordInput(String passwordValue) {
		Waiter.wait(emailInput);
		passwordInput.clear();
		passwordInput.sendKeys(passwordValue);
	}

	public void signInClick() {
		loginBtn.click();
	}
}
