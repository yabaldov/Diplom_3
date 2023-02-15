package org.example.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCredentials {

    private String email;
    private String password;
    private String name;

    public UserCredentials() {
    }

    public UserCredentials(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
