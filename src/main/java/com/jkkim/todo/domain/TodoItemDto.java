package com.jkkim.todo.domain;

import lombok.*;

@Getter
@Setter
public class TodoItemDto {
    private String name;

    private Boolean completed = false;

    public TodoItemDto(String name, Boolean completed) {
        this.name = name;
        this.completed = completed;
    }
}
