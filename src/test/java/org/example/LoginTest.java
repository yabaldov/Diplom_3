package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.base.TestBase;
import org.example.pageobjects.ForgotPasswordPage;
import org.example.pageobjects.MainPage;
import org.example.pageobjects.RegisterPage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends TestBase {

    @Test
    @DisplayName("Should Login Through Enter In Account Button")
    @Description("Вход по кнопке \"Войти в аккаунт\" на главной")
    public void shouldLoginThroughEnterInAccountButton() {
        boolean isLoginSuccessful = new MainPage(rule.getDriver())
                .open()
                .clickEnterInAccountButton()
                .shouldBeOnLoginPage()
                .enter(credentials)
                .shouldBeOnMainPage()
                .isCheckoutButtonDisplayed();
        assertThat(
                "После успешного входа пользователь должен быть на главной странице и там есть кнопка \"Оформить заказ\"",
                isLoginSuccessful,
                is(true));
    }

    @Test
    @DisplayName("Should Login Through Personal Account Button")
    @Description("Вход через кнопку \"Личный кабинет\"")
    public void shouldLoginThroughPersonalAccountButton() {
        boolean isLoginSuccessful = new MainPage(rule.getDriver())
                .open()
                .clickOnPersonalAccountLink()
                .shouldBeOnLoginPage()
                .enter(credentials)
                .shouldBeOnMainPage()
                .isCheckoutButtonDisplayed();
        assertThat(
                "После успешного входа пользователь должен быть на главной странице и там есть кнопка \"Оформить заказ\"",
                isLoginSuccessful,
                is(true));
    }

    @Test
    @DisplayName("Should Login Through the Registration Form Button")
    @Description("Вход через кнопку (ссылку \"Войти\") в форме регистрации")
    public void shouldLoginThroughRegistrationFormButton() {
        boolean isLoginSuccessful = new RegisterPage(rule.getDriver())
                .open()
                .shouldBeOnRegisterPage()
                .clickLoginLink()
                .shouldBeOnLoginPage()
                .enter(credentials)
                .shouldBeOnMainPage()
                .isCheckoutButtonDisplayed();
        assertThat(
                "После успешного входа пользователь должен быть на главной странице и там есть кнопка \"Оформить заказ\"",
                isLoginSuccessful,
                is(true));
    }

    @Test
    @DisplayName("Should Login Through the Password Recovery Form Link")
    @Description("Вход через кнопку (ссылку \"Войти\") в форме восстановления пароля")
    public void shouldLoginThroughPasswordRecoveryFormLink() {
        boolean isLoginSuccessful = new ForgotPasswordPage(rule.getDriver())
                .open()
                .shouldBeOnForgotPasswordPage()
                .clickLoginLink()
                .enter(credentials)
                .shouldBeOnMainPage()
                .isCheckoutButtonDisplayed();
        assertThat(
                "После успешного входа пользователь должен быть на главной странице и там есть кнопка \"Оформить заказ\"",
                isLoginSuccessful,
                is(true));
    }
}
