package com.qa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.CollectionCondition;
import com.qa.enums.EProductSortOption;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class ProductsPage 
{
    private final SelenideElement pageTitle = $("[data-test='title']");
    private final SelenideElement sortDropdown = $("[data-test='product-sort-container']");
    private final SelenideElement cartButton = $("[data-test='shopping-cart-link']");
    private final ElementsCollection products = $$("[data-test='inventory-item']");

    //#region getters
    public String GetFirstProductItemName()
    {
        products.shouldHave(CollectionCondition.sizeGreaterThan(0));
        return GetProductItemName(products.first());
    }

    private String GetProductItemName(SelenideElement product)
    {
        return product.$("[data-test='inventory-item-name']").getText();
    }

    private BigDecimal GetProductItemPrice(SelenideElement product) {
        String priceText = product.$("[data-test='inventory-item-price']").getText().replace("$", "");
        return new BigDecimal(priceText);
    }

    private SelenideElement GetProductItemCartButton(SelenideElement product)
    {
        return product.$("[data-test^='add-to-cart']");
    }

    //#endregion

    public ProductsPage ShouldBeOpened() 
    {
        pageTitle.shouldHave(Condition.text("Products"));
        return this;
    }

    public ProductsPage ShouldContainProducts(int expectedCount) 
    {
        products.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(expectedCount));
        return this;
    }

    public ProductsPage AddFirstProductToCart()
    {
        products.shouldHave(CollectionCondition.sizeGreaterThan(0));
        GetProductItemCartButton(products.first()).click();
        return this;
    }

    public CartPage OpenCart()
    {
        cartButton.click();
        return new CartPage();
    }

    // #region sorting
    public ProductsPage SortProducts(EProductSortOption sortOption) 
    {
        sortDropdown.selectOption(sortOption.getDisplayName());
        return this;
    }

    public ProductsPage ShouldBeSortedSuccessfully(EProductSortOption sortOption)
    {
        switch (sortOption) 
        {
            case PRICE_LOW_TO_HIGH:
                return ShouldBeSortedByPrice(true);
            case PRICE_HIGH_TO_LOW:
                return ShouldBeSortedByPrice(false);
            case NAME_ASCENDING:
                return ShouldBeSortedByName(true);
            case NAME_DESCENDING:
                return  ShouldBeSortedByName(false);
            default:
                throw new IllegalArgumentException(
                        "Unsupported sorting option: " + sortOption);
        }
    }
    
    private ProductsPage ShouldBeSortedByPrice(boolean ascending) 
    {
        BigDecimal previousPrice = BigDecimal.ZERO;
        //System.out.println("Current product prices:");

        for (SelenideElement product : products) 
        {
            BigDecimal currentPrice = GetProductItemPrice(product);
            //System.out.println(currentPrice);

            if (previousPrice.compareTo(BigDecimal.ZERO) != 0) 
            {
                int comprasion = currentPrice.compareTo(previousPrice);

                if (ascending) 
                    assertTrue(comprasion >=0, "Products are not sorted by price low to high.");
                else 
                    assertTrue(comprasion <= 0, "Products are not sorted by price high to low.");
            }

            previousPrice = currentPrice;
        }

        return this;
    }
    
    private ProductsPage ShouldBeSortedByName(boolean ascending) 
    {
        String previousName = "";

        for (SelenideElement product : products) 
        {
            String currentName = GetProductItemName(product);

            if (!previousName.isEmpty()) 
            {
                int comprasion = currentName.compareTo(previousName);

                if (ascending)
                    assertTrue(comprasion >= 0, "Products are not sorted by name A to Z.");
                else
                    assertTrue(comprasion <= 0, "Products are not sorted by name Z to A.");
            }

            previousName = currentName;
        }

        return this;
    }
    //#endregion
}
