package com.example.abstractionizer.login.session1.models.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfo {
    private Integer id;
    private String userName;
    private String email;
    private String phone;
}
