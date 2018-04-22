package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse<T> extends Response {
    private List<T> objects;
    private long currentPage;
    private long numberOfElements;

    public PageResponse(Page<T> objects) {
        this.currentPage = objects.getNumber();
        this.numberOfElements = objects.getNumberOfElements();
        this.objects = objects.getContent();
    }

    public PageResponse(long numberOfE, long currentPage, List<T> list){
        this.numberOfElements = numberOfE;
        this.currentPage = currentPage;
        this.objects = list;
    }
}
