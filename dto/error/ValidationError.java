package com.apress.quickpoll.dto.error;

public class ValidationError {

  private String code;
  private String message;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage(){
    return message;
  }

  public void setMessage(String defaultMessage){
    this.message=message;
  }
}
