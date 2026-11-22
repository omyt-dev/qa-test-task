package com.qa.config;

import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Configuration;

public final class BrowserConfig 
{
    private BrowserConfig() { }

    public static void Configure() 
    {
        Configuration.browser = "chrome";
        Configuration.headless = true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");

        Configuration.browserCapabilities = options;
    }
}
