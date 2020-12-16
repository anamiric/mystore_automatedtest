package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.*;

public class MyStoreTest extends BaseTest {

    private final String EMAIL = "hahacep442@1heizi.com";
    private final String PASSWORD = "123456";
    private final String CITY = "Novi Sad";

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
        buyerLogin.login(EMAIL, PASSWORD);

        AddressCheck addressCheck = new AddressCheck(driver);
        String city = addressCheck.deliveryAddressCheck();
        Assert.assertTrue(city.contains(CITY));
        addressCheck.proceedOnNextPage();

        ShippingAndPayment finalSteps = new ShippingAndPayment(driver);
        finalSteps.checkoutFinalSteps();

        Thread.sleep(3000); // for visual confirmation
    }
}
