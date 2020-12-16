package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ChooseProduct extends BaseHelper {

    private final String URL = "http://automationpractice.com/index.php";

    @FindBy(className = "blockbestsellers")
    WebElement category;

    WebDriver driver;

    public ChooseProduct(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void goToURL() {
        driver.get(URL);
    }

    private void chooseCategory(WebElement element) {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("index")));
        js.executeScript("arguments[0].scrollIntoView();", element);
        click(element);
    }

    private void selectProduct() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("columns")));
        WebElement productContainer = driver.findElement(By.id("blockbestsellers"));
        List<WebElement> productList = productContainer.findElements(By.className("product-container"));
        WebElement product = productList.get(2);
        click(product);
    }

    public void myStoreChooseProduct() {
        goToURL();
        chooseCategory(category);
        selectProduct();
    }

}
