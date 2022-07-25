package com.jkkim.todo.controller;

import com.jkkim.todo.domain.*;
import com.jkkim.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/item",method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    // save
    @GetMapping("/saveItem")
    public void saveTodoItem(@RequestBody TodoItemForm form){
        TodoItem item = TodoItem.createItem(form.getName());
        todoService.saveTodoItem(item);
    }

    // list
    @GetMapping("/list")
    public Result<List<TodoItemDto>> todoItemList(@ModelAttribute("itemSearch") ItemSearch itemSearch, Model model){
        Result<List<TodoItemDto>> result = new Result<>();
        List<TodoItemDto> itemResult = new ArrayList<>();
        List<TodoItem> todoItems = todoService.todoItemList(itemSearch);
        todoItems.stream().forEach(t -> {
            TodoItemDto dto = new TodoItemDto(t.getName(),t.getCompleted());
            itemResult.add(dto);
        });

        result.setData(itemResult);
        return result;
    }

    // update



}
