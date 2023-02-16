package org.example.pageobjects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.example.pageobjects.base.PageBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class MainPage extends PageBase {

    public static final String SITE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(tagName="header")
    private WebElement header;

    @FindBy(xpath = "//header//a[contains(.,'Конструктор')]")
    private WebElement burgerBuilderLink;

    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement builderTitle;

    @FindBy(xpath = "//header//a[contains(.,'Личный Кабинет')]")
    private WebElement personalAccountLink;

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement enterInAccountButton;

    @FindBy(xpath = "//button[text()='Оформить заказ']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//span[text()='Булки']/ancestor::div[1]")
    private WebElement builderBunsMenuTab;

    @FindBy(xpath = "//span[text()='Соусы']/ancestor::div[1]")
    private WebElement builderSaucesMenuTab;

    @FindBy(xpath = "//span[text()='Начинки']/ancestor::div[1]")
    private WebElement builderFillingsMenuTab;

    @FindBy(xpath = "//h2[text()='Булки']")
    private WebElement builderBunsMenuHeader;

    @FindBy(xpath = "//h2[text()='Соусы']")
    private WebElement builderSaucesMenuHeader;
    @FindBy(xpath = "//h2[text()='Начинки']")
    private WebElement builderFillingsMenuHeader;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие главной страницы")
    public MainPage open() {
        driver.get(SITE_URL);
        return (MainPage) waitFor(this, header);
    }

    @Step("Проверка нахождения на главной странице")
    public MainPage shouldBeOnMainPage() {
        return (MainPage) waitFor(this, builderTitle);
    }

    @Step("Переход в личный кабинет с главной страницы")
    public LoginPage clickOnPersonalAccountLink() {
        personalAccountLink.click();
        return new LoginPage(driver);
    }

    @Step("Переход в личный кабинет с главной страницы для вошедшего пользователя")
    public AccountProfilePage clickOnPersonalAccountLinkForLoggedInUser() {
        personalAccountLink.click();
        return new AccountProfilePage(driver);
    }

    @Step("Проверка отображения кнопки \"Оформить заказ\"")
    public boolean isCheckoutButtonDisplayed() {
        return checkoutButton.isDisplayed();
    }

    @Step("Нажать кнопку \"Войти в аккаунт\"")
    public LoginPage clickEnterInAccountButton() {
        enterInAccountButton.click();
        return new LoginPage(driver);
    }

    @Step("Проверка активности ссылки \"Конструктор\"")
    public boolean isBurgerBuilderLinkActive() {
        return (burgerBuilderLink.isDisplayed() &&
                burgerBuilderLink.getAttribute("class")
                        .contains("AppHeader_header__link_active__1IkJo")
        );
    }

    @Step("Кликнуть по вкладке раздела булок в конструкторе")
    public MainPage clickBuilderBunsMenuTab() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(builderBunsMenuTab));
        builderBunsMenuTab.click();
        return this;
    }

    @Step("Проверка, что булки текущий раздел в конструкторе")
    public boolean isBunsCurrentMenuSection() {
        return isCurrentMenuSection(builderBunsMenuTab, builderBunsMenuHeader);
    }

    @Step("Кликнуть по вкладке раздела соусов в конструкторе")
    public MainPage clickBuilderSaucesMenuTab() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(builderSaucesMenuTab));
        builderSaucesMenuTab.click();
        return this;
    }

    @Step("Проверка, что соусы текущий раздел в конструкторе")
    public boolean isSaucesCurrentMenuSection() {
        return isCurrentMenuSection(builderSaucesMenuTab, builderSaucesMenuHeader);
    }

    @Step("Кликнуть по вкладке раздела начинок в конструкторе")
    public MainPage clickBuilderFillingsMenuTab() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(builderFillingsMenuTab));
        builderFillingsMenuTab.click();
        return this;
    }

    @Step("Проверка, что начинки текущий раздел в конструкторе")
    public boolean isFillingsCurrentMenuSection() {
        return isCurrentMenuSection(builderFillingsMenuTab, builderFillingsMenuHeader);
    }

    public boolean isCurrentMenuSection(WebElement menuTab, WebElement menuHeader) {
        return (menuTab.isDisplayed() &&
                menuTab.getAttribute("class").contains("current") &&
                menuHeader.isDisplayed()
        );
    }


    @Step("Прокрутить меню конструктора до раздела булок")
    public MainPage scrollToBunsBuilderSection() {
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", builderBunsMenuHeader);
        return this;
    }


    @Step("Прокрутить меню конструктора до раздела соусов")
    public MainPage scrollToSaucesBuilderSection() {
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", builderSaucesMenuHeader);
        return this;
    }

    @Step("Прокрутить меню конструктора до раздела начинок")
    public MainPage scrollToFillingsBuilderSection() {
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", builderFillingsMenuHeader);
        return this;
    }
}
