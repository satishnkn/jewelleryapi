package com.jewellerypos.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeRequest {
    private String loginId;
    private String otp;
    private String oldPassword;
    private String newPassword;

}
