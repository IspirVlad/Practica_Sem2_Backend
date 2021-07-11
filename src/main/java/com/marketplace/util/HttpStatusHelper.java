package com.marketplace.util;

import com.marketplace.exception.CustomConflictException;
import com.marketplace.exception.CustomEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HttpStatusHelper {


    public static ResponseEntity<Object> success(String objectName, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(objectName, object);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public ResponseEntity<Object> commonErrorMethod(Exception e) {
        if (e instanceof CustomEntityNotFoundException) {
            return objectNotFound((CustomEntityNotFoundException) e);
        } else if (e instanceof CustomConflictException) {
            return conflict((CustomConflictException) e);
        }
        return null;
    }

    private ResponseEntity<Object> conflict(CustomConflictException e) {
        e.printStackTrace();
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getErrorMessage());

        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    public ResponseEntity<Object> objectNotFound(CustomEntityNotFoundException e) {
        e.printStackTrace();
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getErrorMessage());

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
