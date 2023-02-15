package org.example.owner;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:testing.properties")
public interface SettingsConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browserName();

}
