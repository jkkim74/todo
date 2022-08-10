package com.jkkim.todo.domain;

import lombok.Data;

@Data
public class Result<T> {

    private int result;
    private T data;
}
