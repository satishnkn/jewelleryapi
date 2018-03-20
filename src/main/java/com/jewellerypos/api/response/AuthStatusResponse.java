package com.jewellerypos.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthStatusResponse {
    private String cookie;
    private long operatorCode;
    private boolean status;

}
