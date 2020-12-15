package pages;

import helpers.BaseHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddressCheck extends BaseHelper {

    @FindBy (id = "address_delivery")
    WebElement deliveryInfoBox;

    @FindBy (xpath = "/html/body/div/div[2]/div/div[3]/div/form/p/button")
    WebElement proceedCheckoutButton;

    WebDriver driver;

    public AddressCheck(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String deliveryAddressCheck (){
        wdWait.until(ExpectedConditions.visibilityOf(deliveryInfoBox));
        js.executeScript("arguments[0].scrollIntoView();", deliveryInfoBox);
        WebElement addressCity = deliveryInfoBox.findElement(By.className("address_city"));
        return addressCity.getText();
    }
    public void proceedOnNextPage() {
        click(proceedCheckoutButton);
    }
}
