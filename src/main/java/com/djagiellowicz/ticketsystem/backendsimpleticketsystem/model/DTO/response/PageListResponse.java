package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

import java.util.List;

public class PageListResponse<T> {
    private List<T> objectsList;
    private int totalNumberOfElements;

}
