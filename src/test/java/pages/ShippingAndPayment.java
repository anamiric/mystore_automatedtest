package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShippingAndPayment extends BaseHelper {

    @FindBy(id = "carrier_area")
    WebElement shippingBox;

    WebDriver driver;

    public ShippingAndPayment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void checkTerms(WebElement element) {
        wdWait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView();", element);
        WebElement termsOfService = element.findElement(By.id("cgv"));
        click(termsOfService);
        WebElement proceedCheckoutButton = element.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/p/button"));
        click(proceedCheckoutButton);
    }

    private void choosePayment() {

        waitForElementAndScrollIntoView("paiement_block");
        WebElement payByBankWire = driver.findElement(By.className("bankwire"));
        click(payByBankWire);
    }

    private void confirmOrder() {

        waitForElementAndScrollIntoView("cheque-box");
        WebElement confirmOrderButton = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/form/p/button"));
        click(confirmOrderButton);
        waitForElementAndScrollIntoView("box");
    }

    private void waitForElementAndScrollIntoView(String locator) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
        WebElement waitedElement = driver.findElement(By.className(locator));
        js.executeScript("arguments[0].scrollIntoView();", waitedElement);
    }

    public void checkoutFinalSteps() {
        checkTerms(shippingBox);
        choosePayment();
        confirmOrder();
    }
}
