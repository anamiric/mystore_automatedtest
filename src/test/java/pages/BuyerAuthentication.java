package pages;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BuyerAuthentication extends BaseHelper {

    @FindBy(id = "login_form")
    WebElement loginForm;

    @FindBy(id = "email")
    WebElement emailBox;

    @FindBy(id = "passwd")
    WebElement passwordBox;

    @FindBy(id = "SubmitLogin")
    WebElement loginButton;

    WebDriver driver;

    public BuyerAuthentication(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void buyerLogin(WebElement element, String email, String password) {
        wdWait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView();", element);
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        click(loginButton);
    }

    public void login() {

        buyerLogin(loginForm, "hahacep442@1heizi.com", "123456");
    }


}
