package com.jkkim.todo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItemForm {

    private String name;

    public TodoItemForm(String name) {
        this.name = name;
    }
}
