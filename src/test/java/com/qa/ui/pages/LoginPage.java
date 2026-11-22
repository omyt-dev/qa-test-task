package com.qa.ui.pages;

import com.codeborne.selenide.SelenideElement;
import com.qa.config.SauceConfig;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage 
{
    private final SelenideElement usernameInput = $("[data-test='username']");
    private final SelenideElement passwordInput = $("[data-test='password']");
    private final SelenideElement loginButton = $("[data-test='login-button']");

    public LoginPage OpenLoginPage() 
    {
        open(SauceConfig.BASE_URL);
        return this;
    }

    public ProductsPage Login() 
    {
        usernameInput.setValue(SauceConfig.USERNAME);
        passwordInput.setValue(SauceConfig.PASSWORD);
        loginButton.click();

        return new ProductsPage();
    }
    
}
