package org.example.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.example.pageobjects.base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ForgotPasswordPage extends PageBase {

    private static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    @FindBy(xpath = "//h2[text()='Восстановление пароля']")
    private WebElement forgotPasswordHeader;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы восстановления пароля")
    public ForgotPasswordPage open() {
        driver.get(FORGOT_PASSWORD_PAGE_URL);
        return this;
    }

    @Step("Нажать на ссылку \"Войти\"")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return new LoginPage(driver);
    }

    @Step("Проверка нахождения на главной странице")
    public ForgotPasswordPage shouldBeOnForgotPasswordPage() {
        return (ForgotPasswordPage) waitFor(this, forgotPasswordHeader);
    }
}
