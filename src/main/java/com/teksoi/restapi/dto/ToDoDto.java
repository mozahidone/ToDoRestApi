package com.teksoi.restapi.dto;

import lombok.Data;

@Data
public class ToDoDto {
    private Long id;
    private String title;
    private String description;
}
