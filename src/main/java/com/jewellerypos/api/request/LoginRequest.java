package com.jewellerypos.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    
    private String loginId;
    private String loginPwd;
    private String loginDeviceId;

}
