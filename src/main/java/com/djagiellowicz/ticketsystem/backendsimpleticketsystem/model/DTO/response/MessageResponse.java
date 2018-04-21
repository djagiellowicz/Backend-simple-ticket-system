package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

public class MessageResponse extends Response{
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
