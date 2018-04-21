package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

import lombok.Data;

@Data
public class Response {
    private Long timeStamp;

    public Response(){
        this.timeStamp = System.currentTimeMillis();
    }
}
