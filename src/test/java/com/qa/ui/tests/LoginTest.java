package com.qa.ui.tests;

import com.qa.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseUITest
{
    @Test
    public void ShouldLoginSuccessfully()
    {
        new LoginPage()
                .OpenLoginPage()
                .Login()
                .ShouldBeOpened()
                .ShouldContainProducts(0);
    }    
}
