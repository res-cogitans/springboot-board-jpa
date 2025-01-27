package org.programmers.springbootboardjpa.web.controller.api.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//TODO: PR 포인트5
@Slf4j
@RestControllerAdvice(assignableTypes = PostApiController.class)
public class PostApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiErrorData handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("API 오류 응답: 잘못된 인자 전달", e);
        return new ApiErrorData("-202", "Post API에 대하여 잘못된 요청 값 전달");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ApiErrorData handleOtherException(Exception e) {
        log.error("API 오류 응답: 식별되지 않은 오류: ", e);
        return new ApiErrorData("-201", "Post API 관련하여 식별되지 않은 오류가 발생");
    }

    record ApiErrorData(String error_code, String error_description) {
    }
}