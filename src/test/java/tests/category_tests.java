package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class category_tests extends test_base {

    private static final String DESKTOPS_CATEGORY_URL = "http://localhost/opencartproject/index.php?route=product/category&language=en-gb&path=20";
    private static final String COMPONENTS_CATEGORY_URL = "http://localhost/opencartproject/index.php?route=product/category&language=en-gb&path=25";
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void browseCategoryDisplaysProducts() {
        sleep(2000);

        driver.get(DESKTOPS_CATEGORY_URL);
        sleep(2000);

        Assert.assertTrue(category.isCategoryDisplayed("Desktops"), "Desktops category page should be visible");
        String firstProduct = category.getFirstProductName();
        Assert.assertFalse(firstProduct.isEmpty(), "At least one product should be listed in the category");
    }

    @Test
    public void verifyProductSortingOptions() {
        driver.get(COMPONENTS_CATEGORY_URL);

        category.selectSortOption("Price (Low > High)");
        String selectedOption = category.getSelectedSortOption();
        Assert.assertEquals(selectedOption, "Price (Low > High)", "Selected sort option should be applied");

        String firstProductAfterSort = category.getFirstProductName();
        Assert.assertFalse(firstProductAfterSort.isEmpty(), "Products should still be visible after sorting");
    }
}



