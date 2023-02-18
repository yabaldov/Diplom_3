package org.example.rule;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;
import org.example.owner.SettingsConfig;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.getProperties;
import static java.lang.System.getenv;

public class WebDriverRule extends ExternalResource {

    @Getter
    private WebDriver driver;
    private final SettingsConfig config = ConfigFactory.create(SettingsConfig.class, getProperties(), getenv());

    protected void before() throws Throwable {
       if (config.browserName().equals("yandex")) {
           System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver"); //To download YandexDriver: https://github.com/yandex/YandexDriver/releases
           driver = new ChromeDriver();
        } else if (config.browserName().equals("chrome")) {
           driver = new ChromeDriver();
        } else {
            throw new IllegalStateException("Unknown browser. Known browsers are \"chrome\" and \"yandex\".");
        }
    }

    protected void after() {
        driver.quit();
    }
}
