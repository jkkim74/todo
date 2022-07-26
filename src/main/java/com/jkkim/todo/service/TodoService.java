package com.jkkim.todo.service;

import com.jkkim.todo.domain.ItemSearch;
import com.jkkim.todo.domain.QTodoItem;
import com.jkkim.todo.domain.TodoItem;
import com.jkkim.todo.repository.TodoRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long saveTodoItem(TodoItem todoItem){
       TodoItem todo = todoRepository.save(todoItem);
       return todo.getId();
    }

    public List<TodoItem> todoItemList(ItemSearch itemSearch){
        List<TodoItem> result = new ArrayList<>();
        BooleanBuilder builder = new BooleanBuilder();
        QTodoItem item = QTodoItem.todoItem;
        if(!StringUtils.isNullOrEmpty(itemSearch.getName())){
            builder.and(item.name.contains(itemSearch.getName()));
        }
        Iterable<TodoItem> iterItem = todoRepository.findAll(builder);
        iterItem.forEach(c -> {
            result.add(c);
        });
        return result;
    }

    public void deleteTodoItem(Long itemId) {

    }
}
