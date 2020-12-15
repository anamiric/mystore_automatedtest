package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.*;

public class MyStoreTest extends BaseTest {

    @Test
    public void myStoreTest() throws InterruptedException {
        ChooseProduct chooseProduct = new ChooseProduct(driver);
        chooseProduct.myStoreChooseProduct();

        ProductConfirmation confirmProduct = new ProductConfirmation(driver);
        confirmProduct.confirmChosenProduct();

        TotalPriceCheckout priceConfirmation = new TotalPriceCheckout(driver);
        Assert.assertTrue(priceConfirmation.totalPriceSummaryIsCorrect());
        priceConfirmation.proceedOnNextPage();

        BuyerAuthentication buyerLogin = new BuyerAuthentication(driver);
        buyerLogin.login();

        AddressCheck addressCheck = new AddressCheck(driver);
        String city = addressCheck.deliveryAddressCheck();
        Assert.assertTrue(city.contains("Novi Sad"));
        addressCheck.proceedOnNextPage();

        ShippingAndPayment finalSteps = new ShippingAndPayment(driver);
        finalSteps.checkoutFinalSteps();

        Thread.sleep(3000); // for visual confirmation
    }
}
