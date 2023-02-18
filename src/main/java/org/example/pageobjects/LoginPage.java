package org.example.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.example.pageobjects.base.PageBase;
import org.example.user.UserCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage extends PageBase {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private static final String LOGIN_HEADER_TEXT = "Вход";

    @FindBy(xpath = "//h2[contains(.,'Вход')]")
    private WebElement loginHeader;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement inputPassword;

    @FindBy(xpath = "//a[@href='/register']")
    private WebElement registrationLink;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement enterButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы для входа")
    public LoginPage open() {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    @Step("Проверка нахождения на странице входа")
    public LoginPage shouldBeOnLoginPage() {
        return (LoginPage) waitFor(this, loginHeader);
    }

    @Step("Переход по ссылке \"Зарегистрироваться\"")
    public RegisterPage clickOnRegistrationLink() {
        registrationLink.click();
        return new RegisterPage(driver);
    }

    @Step("Заполнить форму для входа")
    public LoginPage fillInLoginForm(UserCredentials credentials) {
        inputEmail.sendKeys(credentials.getEmail());
        inputPassword.sendKeys(credentials.getPassword());
        return this;
    }

    @Step("Нажать на кнопку \"Войти\"")
    public MainPage clickOnEnterButton() {
        enterButton.click();
        return new MainPage(driver);
    }

    @Step("Заполнить форму для входа и нажать \"Войти\"")
    public MainPage enter(UserCredentials credentials) {
        return fillInLoginForm(credentials)
                .clickOnEnterButton();
    }

    @Step("Возвращает признак нахождения на странице входа")
    public boolean isOnLoginPage() {
        waitFor(this, enterButton);
        return enterButton.isDisplayed();
    }
 }
