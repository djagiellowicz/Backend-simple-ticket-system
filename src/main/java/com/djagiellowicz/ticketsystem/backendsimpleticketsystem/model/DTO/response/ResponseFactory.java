package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ResponseFactory {
    public static ResponseEntity<Response> ok(String message){
        return ResponseEntity.ok(new MessageResponse(message));
    }
    public static ResponseEntity<Response> usernameAlreadyExists(){
        return ResponseEntity.ok(new MessageResponse("Username already Exists"));
    }
    public static ResponseEntity<Response> created(){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public static ResponseEntity<Response> badRequest(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    public static <T> ResponseEntity<PageResponse<T>> pageResponse(PageResponse<T> response){
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
