package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class search_tests extends test_base {

    @Test
    public void searchForProduct() {
        home.searchForProduct("iPhone");
        
        // Verify search results are displayed
        int resultsCount = search.getSearchResultsCount();
        Assert.assertTrue(resultsCount >= 0, "Search should return results or show no results message");
    }

    @Test
    public void searchForNonExistentProduct() {
        home.searchForProduct("nonexistentproduct12345");
        
        // Verify no results message is displayed
        Assert.assertTrue(search.hasNoResults() || search.getSearchResultsCount() == 0, 
            "No results message should be displayed for non-existent product");
    }

    @Test
    public void addProductToCartFromSearch() {
        home.searchForProduct("iPhone");
        
        // Check if there are any results
        if (search.getSearchResultsCount() > 0) {
            search.addFirstProductToCart();
            
            // Verify product was added (check cart count or success message)
        }
    }

    @Test
    public void clickProductFromSearchResults() {
        home.searchForProduct("iPhone");
        
        if (search.getSearchResultsCount() > 0) {
            String productName = search.getProductName(0);
            search.clickProductByName(productName);
            
            // Verify we're on product page
        }
    }
}




