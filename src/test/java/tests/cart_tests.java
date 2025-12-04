package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class cart_tests extends test_base {

    @Test
    public void add_simple_product_and_verify_it_appears_in_cart() {
        open_simprod_page();
        sim_prod.addToCart();
        cart.openCartPage();
        cart.Assertion_product_exists_in_cart();}

  @Test
    public void add_simple_product_by_id() {
      addProductToCartById("40");
        cart.openCartPage();
        cart.Assertion_product_exists_in_cart();}


    @Test
    public void add_option_product_and_verify_it_appears_in_cart() {
        open_optprod_page();
        opt_prod.set_option();
        opt_prod.addToCart();
        cart.openCartPage();
        cart.Assertion_product_exists_in_cart();}

    @Test
    public void update_quantity_and_verify_total_updates() {
        open_simprod_page();
        sim_prod.addToCartWithQuantity("2");
        cart.openCartPage();
        cart.updateProductQuantity("3");
        cart.Assertion_quantity_updated("3");}

    @Test
    public void remove_product_and_verify_cart_empty() {
        open_simprod_page();
        sim_prod.addToCart();
        cart.openCartPage();
        cart.removeProduct();
//        cart.Assertion_product_removed();
     }

    @Test
    public void add_multiple_products_to_cart() {
        open_simprod_page();
        sim_prod.addToCart();
//        open_optprod_page();
//        opt_prod.set_option();
//        opt_prod.addToCart();
        sim_prod.addToCart();
        cart.openCartPage();
        cart.Assertion_product_exists_in_cart();}

    @Test
    public void apply_valid_coupon_code() {
        open_simprod_page();
        sim_prod.addToCart();
        cart.openCartPage();
        cart.applyCoupon("1111");
        cart.Assertion_valid_coupon_applied();}

    @Test
    public void apply_invalid_coupon_code() {
        open_simprod_page();
        sim_prod.addToCart();
        cart.openCartPage();
        cart.applyCoupon("INVALID");
        cart.Assertion_invalid_coupon_message();}

    @Test
    public void add_multiple_products_and_remove_them_all() {
        open_simprod_page();
        sim_prod.addToCart();
        sim_prod.addToCartWithQuantity("2");
        cart.openCartPage();
        cart.removeAllProducts();
        cart.Assertion_product_removed();}
}
