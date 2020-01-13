package com.velykorod.productcatalogue.persistance.domain.impl.user;

import com.velykorod.productcatalogue.util.annotations.PasswordMatches;
import com.velykorod.productcatalogue.util.annotations.ValidEmail;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;
}
