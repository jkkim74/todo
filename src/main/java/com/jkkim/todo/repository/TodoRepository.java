package com.jkkim.todo.repository;

import com.jkkim.todo.domain.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TodoRepository extends JpaRepository<TodoItem,Long>, QuerydslPredicateExecutor<TodoItem>, TodoRepositoryCustom {
}
