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
public class RegisterPage extends PageBase {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//label[text()='Имя']/parent::div/input")
    private WebElement inputName;

    @FindBy(xpath = "//label[text()='Email']/parent::div/input")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement inputPassword;

    @FindBy(xpath = "//p[starts-with(@class, 'input__error')]")
    private WebElement errorMessageParagraph;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement loginLink;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы регистрации")
    public RegisterPage open() {
        driver.get(REGISTER_PAGE_URL);
        return this;
    }

    @Step("Проверка нахождения на странице регистрации")
    public RegisterPage shouldBeOnRegisterPage() {
        return (RegisterPage) waitFor(this, registerButton);
    }

    @Step("Заполнить поля формы регистрации")
    public RegisterPage fillInRegistrationForm(UserCredentials credentials) {
        inputName.sendKeys(credentials.getName());
        inputEmail.sendKeys(credentials.getEmail());
        inputPassword.sendKeys(credentials.getPassword());
        return this;
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public LoginPage clickOnRegisterButton() {
        registerButton.click();
        return new LoginPage(driver);
    }

    @Step("Заполнить форму и нажать кнопку \"Зарегистрироваться\"")
    public LoginPage register(UserCredentials credentials) {
        return fillInRegistrationForm(credentials)
                .clickOnRegisterButton();
    }

    @Step("Заполнить форму некорректно и нажать кнопку \"Зарегистрироваться\"")
    public String shouldNotRegister(UserCredentials credentials) {
        fillInRegistrationForm(credentials);
        registerButton.click();
        return errorMessageParagraph.getText();
    };

    @Step("Нажать на ссылку \"Войти\"")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return new LoginPage(driver);
    }
}
