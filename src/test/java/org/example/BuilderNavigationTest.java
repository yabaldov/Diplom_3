package org.example;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobjects.MainPage;
import org.example.rule.WebDriverRule;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BuilderNavigationTest {

    @Rule
    public WebDriverRule rule = new WebDriverRule();

    @Test
    @DisplayName("Should Scroll to Sauces Sections")
    @Description("Проверка, что работает переход к разделу: \"Соусы\"")
    public void shouldScrollToSaucesSections() {
        MainPage mainPage = new MainPage(rule.getDriver()).open();

        boolean isBunsSectionCurrent = mainPage.isBunsCurrentMenuSection();
        assertThat("При открытии конструктора текущим должен быть раздел с булками", isBunsSectionCurrent, is(true) );

        boolean isSaucesSectionCurrent = mainPage.clickBuilderSaucesMenuTab().isSaucesCurrentMenuSection();
        assertThat("При клике по табе \"Соусы\" текущим должен стать раздел с соусами ", isSaucesSectionCurrent, is(true) );
    }

    @Test
    @DisplayName("Should Scroll to Fillings Sections")
    @Description("Проверка, что работает переход к разделу: \"Начинки\"")
    public void shouldScrollToFillingsSections() {
        MainPage mainPage = new MainPage(rule.getDriver()).open();

        boolean isBunsSectionCurrent = mainPage.isBunsCurrentMenuSection();
        assertThat("При открытии конструктора текущим должен быть раздел с булками", isBunsSectionCurrent, is(true) );

        boolean isFillingsSectionCurrent = mainPage.clickBuilderFillingsMenuTab().isFillingsCurrentMenuSection();
        assertThat("При клике по табе \"Начинки\" текущим должен стать раздел с начинками", isFillingsSectionCurrent, is(true) );
    }

    @Test
    @DisplayName("Should Scroll to Fillings Sections")
    @Description("Проверка, что работает переход к разделу: \"Булки\"")
    public void shouldScrollToBunsSections() {
        MainPage mainPage = new MainPage(rule.getDriver()).open();

        boolean isBunsSectionCurrent = mainPage.isBunsCurrentMenuSection();
        assertThat("При открытии конструктора текущим должен быть раздел с булками", isBunsSectionCurrent, is(true) );

        mainPage.clickBuilderFillingsMenuTab();

        isBunsSectionCurrent = mainPage.clickBuilderBunsMenuTab().isBunsCurrentMenuSection();
        assertThat("При клике по табе \"Булки\" текущим должен стать раздел с булками", isBunsSectionCurrent, is(true) );
    }
}