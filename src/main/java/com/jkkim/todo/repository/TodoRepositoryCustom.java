package com.jkkim.todo.repository;

import com.jkkim.todo.domain.ItemSearch;
import com.jkkim.todo.domain.TodoItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoRepositoryCustom {

    Page<TodoItemDto> findTodoItemByPage(ItemSearch itemSearch, Pageable pageable);
}
