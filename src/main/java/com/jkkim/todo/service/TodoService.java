package com.jkkim.todo.service;

import com.jkkim.todo.domain.ItemSearch;
import com.jkkim.todo.domain.QTodoItem;
import com.jkkim.todo.domain.TodoItem;
import com.jkkim.todo.domain.TodoItemForm;
import com.jkkim.todo.repository.TodoRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoItem saveTodoItem(TodoItem todoItem){
       TodoItem todo = todoRepository.save(todoItem);
       return todo;
    }
    @Transactional(readOnly = true)
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

    public int deleteTodoItem(Long itemId) {
        int result = 0;
        try {
            todoRepository.deleteById(itemId);
            result = 1;
        }catch (Exception e){
            log.info("delete exception : {}",e.getMessage());
        }
        return result;
    }

    public int deleteAllTodoItem() {
        int result = 0;
        try {
            todoRepository.deleteAll();
            result = 1;
        }catch (Exception e){
            log.info("delete exception : {}",e.getMessage());
        }
        return result;
    }

    public int updateItem(TodoItemForm form) {
        int result = 0;
        try {
            TodoItem item = todoRepository.findById(form.getId()).get();
            item.setCompleted(form.getCompleted());
            result = 1;
        }catch (Exception e){
            log.info("update exception : {}",e.getMessage());
        }
        return result;
    }
}
