package com.qa.ui.tests;

import com.qa.enums.EProductSortOption;
import com.qa.ui.pages.LoginPage;
import com.qa.ui.pages.ProductsPage;

import org.junit.jupiter.api.Test;

public class ProductsTest extends BaseUITest
{
    @Test 
    public void ShouldSortProducts() 
    {
        new LoginPage()
                .OpenLoginPage()
                .Login()
                .ShouldBeOpened()
                .SortProducts(EProductSortOption.PRICE_LOW_TO_HIGH)
                .ShouldBeSortedSuccessfully(EProductSortOption.PRICE_LOW_TO_HIGH)
                .SortProducts(EProductSortOption.PRICE_HIGH_TO_LOW)
                .ShouldBeSortedSuccessfully(EProductSortOption.PRICE_HIGH_TO_LOW)
                .SortProducts(EProductSortOption.NAME_ASCENDING)
                .ShouldBeSortedSuccessfully(EProductSortOption.NAME_ASCENDING)
                .SortProducts(EProductSortOption.NAME_DESCENDING)
                .ShouldBeSortedSuccessfully(EProductSortOption.NAME_DESCENDING);
    }

    @Test 
    public void ShouldAddProductToCart()
    {
        ProductsPage productsPage = new LoginPage()
            .OpenLoginPage()
            .Login()
            .ShouldBeOpened()
            .AddFirstProductToCart();
        
        String productName = productsPage.GetFirstProductItemName();

        productsPage.OpenCart()
            .ShouldBeOpened()
            .ShouldContainProduct(productName);
    }
}
