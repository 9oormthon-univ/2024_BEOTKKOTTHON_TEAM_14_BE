package com.example.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 400 BAD REQUEST
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 헤더값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 파라미터값이 입력되지 않았습니다."),
    REQUEST_METHOD_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 메소드가 잘못됐습니다."),
    MAX_UPLOAD_SIZE_EXCEED_EXCEPTION(HttpStatus.PAYLOAD_TOO_LARGE, "파일 용량 초과"),

    /**
     * 401 UNAUTHORIZED
     */
    INVALID_PASSWORD_EXCEPTION(HttpStatus.UNAUTHORIZED, "일치하지 않는 비밀번호입니다."),
    TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    NOT_FOUND_IMAGE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 이미지입니다."),
    NOT_FOUND_WILL(HttpStatus.NOT_FOUND, "존재하지 않는 유서입니다."),
    NOT_FOUND_ANSWER(HttpStatus.NOT_FOUND, "존재하지 않는 질문의 답변입니다."),


    /**
     * 409 Conflict
     */
    DUPLICATE_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저입니다."),
    DUPLICATE_WILL_EXCEPTION(HttpStatus.CONFLICT, "유서를 이미 작성하셨습니다. 유서는 한 사람 당 한개입니다."),
    DUPLICATE_ANSWER_EXCEPTION(HttpStatus.CONFLICT, "질문을 이미 답변하셨습니다."),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
