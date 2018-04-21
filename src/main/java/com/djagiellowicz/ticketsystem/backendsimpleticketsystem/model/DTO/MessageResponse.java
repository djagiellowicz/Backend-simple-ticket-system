package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO;

public class MessageResponse extends Response{
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
