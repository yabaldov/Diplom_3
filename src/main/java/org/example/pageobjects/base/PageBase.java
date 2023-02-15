package org.example.pageobjects.base;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

    protected WebDriver driver;

    @Step("Ожидание элемента")
    public PageBase waitFor(PageBase page, WebElement element) {
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOf(element));
        return page;
    }
}
