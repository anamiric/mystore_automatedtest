package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;

public class ProductConfirmation extends BaseTest {

    @FindBy (id = "center_column")
    WebElement productBox;

    WebDriver driver;

    public ProductConfirmation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void setSizeAndColor (WebElement element) {
        wdWait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView();", element);
        WebElement sizeSelector = element.findElement(By.id("group_1"));
        click(sizeSelector);
        WebElement sizeL = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[2]/div/fieldset[1]/div/div/select/option[3]"));
        click(sizeL);
        WebElement whiteColorPicker = element.findElement(By.id("color_8"));
        click(whiteColorPicker);
        WebElement addToCartButton = element.findElement(By.className("exclusive"));
        click(addToCartButton);
    }

    private void popUpProductConfirmation () {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
        WebElement popUp= driver.findElement(By.id("layer_cart"));
        WebElement proceedButton = popUp.findElement(By.className("button-medium"));
        click(proceedButton);
    }

    public void confirmChosenProduct () {
        setSizeAndColor(productBox);
        popUpProductConfirmation();
    }
}
