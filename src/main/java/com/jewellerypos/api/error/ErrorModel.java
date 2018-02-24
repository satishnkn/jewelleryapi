package com.jewellerypos.api.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel  {
  private String code = null;
  private String status = null;
  private String message = null;

}

