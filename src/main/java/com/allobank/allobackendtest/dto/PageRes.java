package com.allobank.allobackendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRes<T> {
    private Integer status;
    private String message;
    private List<T> data;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalCount;
}
