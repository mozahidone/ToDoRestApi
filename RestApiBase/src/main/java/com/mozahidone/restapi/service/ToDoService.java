package com.mozahidone.restapi.service;

import com.mozahidone.restapi.dto.Response;
import com.mozahidone.restapi.dto.ToDoDto;

public interface ToDoService {
    Response create(ToDoDto toDoDto);

    Response getAll();

    Response update(Long id, ToDoDto toDoDto);

    Response delete(Long id);

    Response deleteAll();
}
