package com.avyrodov.bugTracker.web.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm {
    private String firstname;
    private String surname;
    private String username;
    private String password;
}
