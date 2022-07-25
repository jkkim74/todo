package com.jkkim.todo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoItem {
    @Id
    @GeneratedValue
    @Column(name = "todo_item_id")
    private Long id;

    private String name;

    private Boolean completed = false;

    public static TodoItem createItem(String name) {
        TodoItem item = new TodoItem();
        item.name = name;
        return item;
    }

    public void modifyItem(TodoItemDto todoItemDto){
        this.name = todoItemDto.getName();
        this.completed = todoItemDto.getCompleted();

    }

}
