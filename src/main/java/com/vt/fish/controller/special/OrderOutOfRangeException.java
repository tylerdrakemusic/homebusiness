package com.vt.fish.controller.special;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (code = HttpStatus.BAD_REQUEST)
public class OrderOutOfRangeException extends RuntimeException {
    public OrderOutOfRangeException(String s) {
        super(s);
    }
}
