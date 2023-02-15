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
public class AccountProfilePage extends PageBase {

    private static final String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private static final String ACTIVE_LINK_CLASS_NAME = "Account_link_active__2opc9";

    @FindBy(xpath = "//a[@href='/account/profile']")
    private WebElement profileLink;

    @FindBy(xpath = "//label[text()='Имя']/parent::div/input")
    private WebElement inputName;

    @FindBy(xpath = "//label[text()='Логин']/parent::div/input")
    private WebElement inputLoginEmail;

    @FindBy(xpath = "//label[text()='Пароль']/parent::div/input")
    private WebElement inputPassword;

    @FindBy(xpath = "//header//a[contains(.,'Конструктор')]")
    private WebElement burgerBuilderLink;

    @FindBy(className = "AppHeader_header__logo__2D0X2")
    private WebElement stellarBurgersLogo;

    @FindBy(xpath = "//button[text()='Выход']")
    private WebElement exitButton;

    public AccountProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка, что ссылка \"Профиль\" активна")
    public boolean isProfileLinkActive() {
        waitFor(this, profileLink);
        return (profileLink.isDisplayed() &&
                profileLink.getAttribute("class").contains(ACTIVE_LINK_CLASS_NAME)
        );
    }

    @Step("Получить данные пользователя из формы профиля")
    public UserCredentials getCredentialFromProfileForm() {
        return new UserCredentials(
                inputLoginEmail.getAttribute("value"),
                inputPassword.getAttribute("value"),
                inputName.getAttribute("value")
        );
    }

    @Step("Проверка нахождения в личном кабинете \"Профиль\"")
    public AccountProfilePage shouldBeOnAccountProfilePage() {
        return (AccountProfilePage) waitFor(this, profileLink);
    }

    @Step("Нажать на ссылку \"Конструктор\" из личного кабинета")
    public MainPage clickOnBurgerBuilderLink() {
        burgerBuilderLink.click();
        return new MainPage(driver);
    }

    @Step("Нажать на логотип \"Stellar Burgers\" из личного кабинета")
    public MainPage clickOnStellarBurgersLogo() {
        stellarBurgersLogo.click();
        return new MainPage(driver);
    }

    @Step("Нажать на кнопку \"Выход\"")
    public LoginPage clickOnExitButton() {
        exitButton.click();
        return new LoginPage(driver);
    }
}
