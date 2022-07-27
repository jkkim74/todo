package com.jkkim.todo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItemForm {

    private Long id;
    private String name;
    private Boolean completed;

//    public TodoItemForm(String name) {
//        this.name = name;
//    }
}
