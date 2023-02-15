package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobjects.MainPage;
import org.example.pageobjects.RegisterPage;
import org.example.rule.WebDriverRule;
import org.example.user.UserCredentials;
import org.example.user.UserDataProvider;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTest {

    @Rule
    public WebDriverRule rule = new WebDriverRule();

    @Test
    @DisplayName("Should Register a User Successfully")
    @Description("Тест успешной регистрации. (После можно войти и есть кнопка оформления заказа)")
    public void shouldRegisterUserSuccessfully() {
        UserCredentials credentials = UserDataProvider.getUserWithRandomData();
        boolean checkoutButtonIsOnPage = new MainPage(rule.getDriver())
                .open()
                .shouldBeOnMainPage()
                .clickOnPersonalAccountLink()
                .shouldBeOnLoginPage()
                .clickOnRegistrationLink()
                .shouldBeOnRegisterPage()
                .register(credentials)
                .shouldBeOnLoginPage()
                .enter(credentials)
                .shouldBeOnMainPage()
                .isCheckoutButtonDisplayed();
        assertThat(
                "После успешной регистрации на главной странице есть кнопка \"Оформить заказ\"",
                checkoutButtonIsOnPage,
                is(true));
    }

    @Test
    @DisplayName("Should Not Register With a Short Password")
    @Description("Тест ошибки для некорректного пароля при регистрации. (Минимальный пароль — 6 символов)")
    public void shouldNotRegisterWithShortPassword() {
        UserCredentials credentials = UserDataProvider.getUserWithShortPassword();
        String errorMessage = new RegisterPage(rule.getDriver())
                .open()
                .shouldBeOnRegisterPage()
                .shouldNotRegister(credentials);
        assertThat(
                "При регистрации с некорректным ожидается сообщение об ошибке",
                errorMessage,
                is("Некорректный пароль"));
    }
}
