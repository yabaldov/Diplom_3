package org.example.base;

import org.example.helper.RegisterHelper;
import org.example.rule.WebDriverRule;
import org.example.user.UserCredentials;
import org.example.user.UserDataProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public abstract class TestBase {
    @Rule
    public WebDriverRule rule = new WebDriverRule();

    protected RegisterHelper registerHelper;
    protected UserCredentials credentials;

    @Before
    public void setUp() {
        credentials = UserDataProvider.getUserWithRandomData();
        registerHelper = new RegisterHelper();
        registerHelper.registerUser(credentials);
    }

    @After
    public void tearDown() {
        registerHelper.deleteUser();
    }
}
