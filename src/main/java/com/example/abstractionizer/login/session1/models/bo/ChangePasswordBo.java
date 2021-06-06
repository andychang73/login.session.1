package com.example.abstractionizer.login.session1.models.bo;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ChangePasswordBo {

    @NotEmpty(message = "must not be null nor empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_+])(?=\\S+$).{8,}$", message = "invalid format")
    private String oldPassword;

    @NotEmpty(message = "must not be null nor empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_+])(?=\\S+$).{8,}$", message = "invalid format")
    private String newPassword;

    @NotEmpty(message = "must not be null nor empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()_+])(?=\\S+$).{8,}$", message = "invalid format")
    private String confirmPassword;

}
