package com.sparta.myselectshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//공통적으로 처리해주기 위한 스프링에서 제공하는 어노테이션
//모든 Controller에서 발생하는 예외처리를 하기위해 글로벌적으로 사용
//클래스 레벨의 어노테이션
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
    * ControllerAdvice를 사용하는 이유
    * 1. 예외처리를 중앙집중화 하기 좋음
    * 2. 코드의 중복을 방지, 유지보수성을 높임
    * 3. 예외처리 로직들을 모듈화해서 관리하기 쉽기 떄문에 팀 내에서 공통된 예외처리 로직을 공유하거나, 다른 팀에서 예외처리를 참고할 수 있음
    *  -> 전체적으로 개발에 대한 생산성을 향상시켜줌
    * */

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<RestApiException> handleException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.BAD_REQUEST
        );
    }

    // nullPointerException
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<RestApiException> nullPointerExceptionHandler(NullPointerException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.NOT_FOUND
        );
    }

    //직접 Custom한 Exception
    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<RestApiException> notFoundProductExceptionHandler(ProductNotFoundException ex) {
        RestApiException restApiException = new RestApiException(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(
                // HTTP body
                restApiException,
                // HTTP status code
                HttpStatus.NOT_FOUND
        );
    }
}
