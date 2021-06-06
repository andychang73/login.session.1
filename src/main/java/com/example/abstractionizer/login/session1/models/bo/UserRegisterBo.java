package com.example.abstractionizer.login.session1.models.bo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserRegisterBo {

    @NotEmpty(message = "cannot be null nor empty")
    private String userName;

    @NotEmpty(message = "cannot be null nor empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_+])(?=\\S+$).{8,}$", message = "invalid format")
    private String password;

    @NotEmpty(message = "cannot be null nor empty")
    @Pattern(regexp = "^(.*)@(.*)$", message = "invalid format")
    private String email;

    @NotEmpty(message = "cannot be null nor empty")
    @Pattern(regexp = "^09\\d{8}$", message = "invalid format")
    private String phone;

}
