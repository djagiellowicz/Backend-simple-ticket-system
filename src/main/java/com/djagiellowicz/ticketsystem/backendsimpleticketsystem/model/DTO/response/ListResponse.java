package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> extends Response {
    private List<T> objects;

    public ListResponse(List<T> objects) {
        super();
        this.objects = objects;
    }
}
