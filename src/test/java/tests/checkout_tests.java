package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class checkout_tests extends test_base {

    @Test
    public void guest_checkout_end_to_end() {
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        // Guest Checkout Steps
        checkout.selectGuestCheckout();
       checkout.fillGuestPersonalDetails("guest", "guestflow", "guest@gmail.com");
       checkout.fillGuestShippingAddress("company", "address1", "address2", "London", "postcode", "United Kingdom", "Greater London");
        checkout.clickContinueButton();


        checkout.Assertion_billing_details_saved();
        checkout.chooseShippingMethod();
        checkout.selectShippingMethodInModal("free.free");
        checkout.choosePaymentMethod();
        checkout.selectPaymentMethodInModal("cod.cod");
        checkout.agreeToTerms();
        checkout.confirmOrder();
        checkout.Assertion_order_confirmed();}

    @Test
    public void registered_user_checkout() {
        loginWithExistingAccount();
        account.open_hmpage();
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        checkout.selectExistingShippingAddressByIndex(1);
        checkout.Assertion_billing_details_saved();
        checkout.chooseShippingMethod();
        checkout.selectShippingMethodInModal("flat.flat");
        checkout.choosePaymentMethod();
        checkout.selectPaymentMethodInModal("cod.cod");
        checkout.agreeToTerms();
        checkout.confirmOrder();
        checkout.Assertion_order_confirmed();}

    @Test
    public void billing_form_validation() {
        loginWithExistingAccount();
        account.open_hmpage();
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        checkout.fillNewShippingAddress("Test", "User", "Test Company", "123 Test Street", "", "London", "SW1A 1AA", "United Kingdom", "Greater London");
        checkout.Assertion_billing_details_saved();}

    @Test
    public void order_confirmation_validation() {
        loginWithExistingAccount();
        account.open_hmpage();
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        checkout.selectExistingShippingAddressByIndex(5);
        checkout.chooseShippingMethod();
        checkout.selectShippingMethodInModal("flat.flat");
        checkout.choosePaymentMethod();
        checkout.selectPaymentMethodInModal("cod.cod");
        checkout.agreeToTerms();
        checkout.confirmOrder();
        checkout.Assertion_order_confirmed();}

    @Test
    public void checkoutWithNewShippingAddress() {
        loginWithExistingAccount();
        account.open_hmpage();
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        checkout.fillNewShippingAddress("nashat", "mohamed", "Test Company", "123 Test Street", "", "London", "SW1A 1AA", "United Kingdom", "Greater London");
        checkout.chooseShippingMethod();
        checkout.selectShippingMethodInModal("free.free");
        checkout.choosePaymentMethod();
        checkout.selectPaymentMethodInModal("cod.cod");
        checkout.agreeToTerms();
        checkout.confirmOrder();
        checkout.Assertion_order_confirmed();}

    @Test
    public void checkoutWithComment() {
        loginWithExistingAccount();
        account.open_hmpage();
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        checkout.selectExistingShippingAddressByIndex(3);
        checkout.chooseShippingMethod();
        checkout.selectShippingMethodInModal("free.free");
        checkout.choosePaymentMethod();
        checkout.selectPaymentMethodInModal("cod.cod");
        checkout.addComment("Please deliver in the morning");
        checkout.agreeToTerms();
        checkout.confirmOrder();
        checkout.Assertion_order_confirmed();}

    @Test
    public void selectShippingAndPaymentMethods() {
        loginWithExistingAccount();
        account.open_hmpage();
        home.open_simpleprod_Page();
        sim_prod.addToCart();
        checkout.opencheckoutPage();
        checkout.Assertion_checkout_page_opened();
        checkout.selectExistingShippingAddressByIndex(1);
        checkout.chooseShippingMethod();
        checkout.selectShippingMethodInModal("free.free");
        checkout.choosePaymentMethod();
        checkout.selectPaymentMethodInModal("cod.cod");
        checkout.Assertion_billing_details_saved();}
}
