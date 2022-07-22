package com.jkkim.todo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoItem {
    @Id
    @GeneratedValue
    @Column(name = "todo_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public static TodoItem createTodoItem(Item item){
        TodoItem todoItem = new TodoItem();
        todoItem.setItem(item);
        return todoItem;
    }
}
