package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO;

import lombok.Data;

@Data
public class Response {
    private Long timeStamp;

    public Response(){
        this.timeStamp = System.currentTimeMillis();
    }
}
