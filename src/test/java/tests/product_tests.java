package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Simple_ProductPage;

public class product_tests extends test_base {

    @Test
    public void open_simbleproduct_page() {
        open_simprod_page();
        sim_prod.Assertion_open_simprod_page();
    }
    @Test
    public void Check_desc_sec() {
        open_simprod_page();
        sim_prod.open_prod_descs();
        sim_prod.Assertion_open_simprod_desc();
    }
    @Test
    public void Check_specs_sec() {
        open_simprod_page();
        sim_prod.open_prod_specs();
        sim_prod.Assertion_open_simprod_specs();
    }
    @Test
    public void Check_review_sec() {
        open_simprod_page();
        sim_prod.open_prod_reviews();
        sim_prod.Assertion_open_simprod_revs();
    }
    @Test
    public void add_prodwithoutqnt_tocart() {
        open_simprod_page();
        sim_prod.addToCart();
        sim_prod.Assertion_addtocart_withoutqnt();
    }
    @Test
    public void add_prodwithqnt_tocart() {
        String qnt = "5";
        open_simprod_page();
        sim_prod.addToCartWithQuantity(qnt);
        sim_prod.Assertion_addtocart_withqnt(qnt);
    }
    @Test
    public void add_prod_to_wichlist() {
        open_simprod_page();
        sim_prod.addToWishlist();
        sim_prod.Assertion_addprod_towishlist();
    }

    @Test
    public void open_optionproduct_page() {
        open_optprod_page();
        opt_prod.Assertion_open_optrod_page();
    }
    @Test
    public void add_optprodwithoutqnt_tocart_withnoopt() {
        open_optprod_page();
        opt_prod.addToCart_withno_opt();
        opt_prod.Assertion_addtocart_withnoopt();
    }
    @Test
    public void add_optprodwithoutqnt_tocart() {
        open_optprod_page();
        opt_prod.addToCart();
        opt_prod.Assertion_addtocart_withoutqnt();
    }
    @Test
    public void add_optprodwithqnt_tocart() {
        String qnt = "5";
        open_optprod_page();
        opt_prod.addToCartWithQuantity(qnt);
        opt_prod.Assertion_addtocart_withqnt(qnt);
    }
}

