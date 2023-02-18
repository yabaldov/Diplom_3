package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.base.TestBase;
import org.example.pageobjects.LoginPage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ExitTest extends TestBase {

    @Test
    @DisplayName("Should Navigate To Personal Account")
    @Description("Проверь выхода из аккаунта по кнопке \"Выйти\" в личном кабинете")
    public void shouldAbleToExitFromPersonalAccount() {
       boolean isExitSuccessful =  new LoginPage(rule.getDriver())
                .open()
                .enter(credentials)
                .shouldBeOnMainPage()
                .clickOnPersonalAccountLinkForLoggedInUser()
                .shouldBeOnAccountProfilePage()
                .clickOnExitButton()
                .isOnLoginPage();
       assertThat(
               "После успешного выхода из личного кабинета пользователь должен быть на странице входа (login)",
               isExitSuccessful,
               is(true));
    }
}
