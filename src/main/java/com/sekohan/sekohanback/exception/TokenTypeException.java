package com.sekohan.sekohanback.exception;

import org.springframework.http.HttpStatus;

public class TokenTypeException extends CustomException{
    public TokenTypeException() {
        super(HttpStatus.BAD_REQUEST, "잘못된 타입");
    }
    public static final CustomException EXCEPTION = new TokenTypeException();

}
