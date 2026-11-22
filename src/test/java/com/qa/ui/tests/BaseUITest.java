package com.qa.ui.tests;

import com.qa.config.BrowserConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseUITest 
{
    @BeforeAll
    public static void SetUp() 
    {
        BrowserConfig.Configure();
    }

    @AfterAll
    public static void TearDown() 
    {
        closeWebDriver();
    }
}
