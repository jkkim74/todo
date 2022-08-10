package com.jkkim.todo.repository;

import com.jkkim.todo.domain.ItemSearch;
import com.jkkim.todo.domain.QTodoItemDto;
import com.jkkim.todo.domain.TodoItemDto;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jkkim.todo.domain.QTodoItem.todoItem;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<TodoItemDto> findTodoItemByPage(ItemSearch itemSearch, Pageable pageable) {
        QueryResults<TodoItemDto> results = queryFactory.select(new QTodoItemDto(
                        todoItem.id,
                        todoItem.name,
                        todoItem.completed
                )).from(todoItem)
                .orderBy(todoItem.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<TodoItemDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);
    }
}
