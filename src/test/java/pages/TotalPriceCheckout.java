package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TotalPriceCheckout extends BaseHelper {

    @FindBy(id = "center_column")
    WebElement shoppingCartSummary;

    @FindBy(className = "standard-checkout")
    WebElement proceedCheckoutButton;

    @FindBy(id = "total_price")
    WebElement totalPrice;

    WebDriver driver;

    public TotalPriceCheckout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean totalPriceSummaryIsCorrect() {

        WebElement currentTotalPrice = totalPrice;
        String currentTotalPriceText = currentTotalPrice.getText();
        quantityCheck("4", shoppingCartSummary);
        double productPrice = getProductPrice(shoppingCartSummary);
        System.out.println("Product price:" + productPrice);
        double shippingPrice = getShippingPrice(shoppingCartSummary);
        System.out.println("Shipping price:" + shippingPrice);
        double totalOrderPrice = getTotalPrice(currentTotalPriceText);
        System.out.println("Total price:" + totalOrderPrice);
        return totalOrderPrice == ((productPrice * 4) + shippingPrice);
    }

    public void proceedOnNextPage() {
        click(proceedCheckoutButton);
    }

    private void quantityCheck(String number, WebElement element) {
        wdWait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView();", element);
        WebElement quantityBox = element.findElement(By.className("cart_quantity_input"));
        click(quantityBox);
        quantityBox.sendKeys(number);
    }

    private double getProductPrice(WebElement element) {

        WebElement unitPrice = element.findElement(By.className("price"));
        return getValueFromTextField(unitPrice);
    }

    private double getShippingPrice(WebElement element) {

        WebElement totalShipping = element.findElement(By.id("total_shipping"));
        return getValueFromTextField(totalShipping);
    }

    private double getTotalPrice(String previousTotal) {

        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("total_price"), previousTotal));
        return getValueFromTextField(totalPrice);
    }

    private double getValueFromTextField(WebElement element) {
        String text = element.getText();
        text = text.replace("$", "");
        return Double.parseDouble(text);
    }
}
