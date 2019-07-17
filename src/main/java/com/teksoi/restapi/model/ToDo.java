package com.teksoi.restapi.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class ToDo extends BaseModel {

    private String title;
    private String description;
}