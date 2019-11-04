package com.apress.quickpoll.handler;

import com.apress.quickpoll.dto.error.ErrorDetail;
import com.apress.quickpoll.dto.error.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable (HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

    ErrorDetail errorDetail = new ErrorDetail();
    errorDetail.setTimeStamp(new Date().getTime());
    errorDetail.setStatus(status.value());
    errorDetail.setTitle("Message not readable");
    errorDetail.setDetail(ex.getMessage());
    errorDetail.setDeveloperMessage(ex.getClass().getName());

    return handleExceptionInternal(ex, errorDetail, headers, status, request);

  }
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request){
      ErrorDetail errorDetail = new ErrorDetail();
      errorDetail.setTimeStamp(new Date().getTime());
      errorDetail.setStatus(status.value());
      errorDetail.setTitle("Message not readable");
      errorDetail.setDetail(manve.getMessage());
      errorDetail.setDeveloperMessage(manve.getClass().getName());

      return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

}
