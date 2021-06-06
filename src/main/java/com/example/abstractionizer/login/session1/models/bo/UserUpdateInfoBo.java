package com.example.abstractionizer.login.session1.models.bo;

import com.example.abstractionizer.login.session1.annotations.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UserUpdateInfoBo {

    @NullOrNotBlank(message = "can be null but not empty")
    private String userName;

    @NullOrNotBlank(message = "can be null but not empty")
    @Pattern(regexp = "^(.*)@(.*)$", message = "invalid format")
    private String email;

    @NullOrNotBlank(message = "can be null but not empty")
    @Pattern(regexp = "^09\\d{8}", message = "invalid format")
    private String phone;
}
