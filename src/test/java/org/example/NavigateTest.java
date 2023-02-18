package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.base.TestBase;
import org.example.pageobjects.AccountProfilePage;
import org.example.pageobjects.LoginPage;
import org.example.user.UserCredentials;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NavigateTest extends TestBase {

    @Test
    @DisplayName("Should Navigate To Personal Account")
    @Description("Проверка перехода по клику на \"Личный кабинет\"")
    public void shouldNavigateToPersonalAccount() {
        AccountProfilePage accountProfilePage  = new LoginPage(rule.getDriver())
                .open()
                .enter(credentials)
                .shouldBeOnMainPage()
                .clickOnPersonalAccountLinkForLoggedInUser();

        boolean isInPersonalAccount =  accountProfilePage.isProfileLinkActive();
        UserCredentials credentialsFromProfileForm = accountProfilePage.getCredentialFromProfileForm();
        assertThat(
                "После перехода вошедшего пользователя он должен попасть в личный кабинет",
                isInPersonalAccount,
                is(true));
        assertThat(
                "Форма в личном кабинете должна содержать имя вошедшего пользователя",
                credentialsFromProfileForm.getName(),
                is(credentials.getName()));
        assertThat(
                "Форма в личном кабинете должна содержать имя вошедшего пользователя",
                credentialsFromProfileForm.getEmail(),
                is(credentials.getEmail()));
    }

    @Test
    @DisplayName("Should Navigate From Personal Account  to Burger Builder")
    @Description("Проверка переход из личного кабинета по клику на \"Конструктор\"")
    public void shouldNavigateFromPersonalAccountToBurgerBuilder() {
        boolean isOnBurgerBuilderPage = new LoginPage(rule.getDriver())
                .open()
                .enter(credentials)
                .shouldBeOnMainPage()
                .clickOnPersonalAccountLinkForLoggedInUser()
                .shouldBeOnAccountProfilePage()
                .clickOnStellarBurgersLogo()
                .shouldBeOnMainPage()
                .isBurgerBuilderLinkActive();
        assertThat(
                "После перехода из личного кабинета пользователь должен попасть в \"Конструктор\"",
                isOnBurgerBuilderPage,
                is(true));
    }

    @Test
    @DisplayName("Should Navigate To Burger Builder By Clicking on the Logo")
    @Description("Проверка переход из личного кабинета по клику на логотип \"Stellar Burgers\"")
    public void shouldNavigateToBurgerBuilderByClickingOnLogo() {
        boolean isOnBurgerBuilderPage = new LoginPage(rule.getDriver())
                .open()
                .enter(credentials)
                .shouldBeOnMainPage()
                .clickOnPersonalAccountLinkForLoggedInUser()
                .shouldBeOnAccountProfilePage()
                .clickOnBurgerBuilderLink()
                .shouldBeOnMainPage()
                .isBurgerBuilderLinkActive();
        assertThat(
                "После перехода из личного кабинета пользователь должен попасть в \"Конструктор\"",
                isOnBurgerBuilderPage,
                is(true));
    }
}
