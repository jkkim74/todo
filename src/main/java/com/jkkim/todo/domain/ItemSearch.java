package com.jkkim.todo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearch {
    private String name;
    private int offset = 0;
    private int limit = 0;

}
