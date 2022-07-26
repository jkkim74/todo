package com.jkkim.todo.domain;

import lombok.*;

@Getter
@Setter
public class TodoItemDto {
    private Long id;
    private String name;

    private Boolean completed = false;

    public TodoItemDto(Long id, String name, Boolean completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }
}
