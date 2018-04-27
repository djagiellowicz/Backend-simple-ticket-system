package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

import lombok.Data;

@Data
public class ObjectResponse<T> extends Response {
    private T object;

    public ObjectResponse(T object) {
        super();
        this.object = object;
    }
}
