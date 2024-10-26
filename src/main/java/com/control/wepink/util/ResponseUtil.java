package com.control.wepink.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseUtil {

    public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status, Object obj) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("msg", msg);
        map.put("status", status.value());
        map.put("data", obj);

        return new ResponseEntity<>(map, status);
    }

}
