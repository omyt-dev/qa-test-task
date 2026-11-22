package com.qa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage 
{
    private final SelenideElement pageTitle = $("[data-test='title']");
    private final ElementsCollection cartItems = $$("[data-test='inventory-item']");

    private SelenideElement GetCartItemNameElement(SelenideElement cartItem)
    {
        return cartItem.$("[data-test='inventory-item-name']");
    }

    public CartPage ShouldBeOpened() 
    {
        pageTitle.shouldHave(Condition.text("Your Cart"));
        return this;
    }

    public CartPage ShouldContainProduct(String name)
    {
        cartItems.shouldHave(CollectionCondition.size(1));
        GetCartItemNameElement(cartItems.first()).shouldHave(Condition.text(name));
        return this;
    }
}
