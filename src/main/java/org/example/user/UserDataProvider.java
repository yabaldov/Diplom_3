package org.example.user;

import com.github.javafaker.Faker;

public class UserDataProvider {

    public static UserCredentials getDefaultUser() {
        return new UserCredentials(
                "celestial0876@host.local",
                "sUw#7peM",
                "celestial0876"
        );
    }

    public static UserCredentials getUserWithRandomData() {
        Faker faker = new Faker();
        String email = faker.internet().safeEmailAddress();
        String password = faker.internet().password(6, 7);
        String name = email.split("@")[0];

        return new UserCredentials(email, password, name);
    }

    public static UserCredentials getUserWithShortPassword() {
        Faker faker = new Faker();
        String email = faker.internet().safeEmailAddress();
        String password = faker.internet().password(5, 6);
        String name = email.split("@")[0];

        return new UserCredentials(email, password, name);
    }
}
