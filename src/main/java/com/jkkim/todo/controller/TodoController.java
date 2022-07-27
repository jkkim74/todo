package com.jkkim.todo.controller;

import com.jkkim.todo.domain.*;
import com.jkkim.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/items",method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    // save
    @PutMapping("/saveItem")
    public TodoItem saveTodoItem(@RequestBody TodoItemForm form){
        TodoItem item = TodoItem.createItem(form.getName());
        TodoItem newItem = todoService.saveTodoItem(item);
        return newItem;
    }

    // list
    @GetMapping("/list")
    public Result<List<TodoItemDto>> todoItemList(@ModelAttribute("itemSearch") ItemSearch itemSearch, Model model){
        Result<List<TodoItemDto>> result = new Result<>();
        List<TodoItemDto> itemResult = new ArrayList<>();
        List<TodoItem> todoItems = todoService.todoItemList(itemSearch);
        todoItems.stream().forEach(t -> {
            TodoItemDto dto = new TodoItemDto(t.getId(),t.getName(),t.getCompleted());
            itemResult.add(dto);
        });

        result.setData(itemResult);
        return result;
    }

    // update

    // delete
    @DeleteMapping("/delete/{itemId}")
    public Result<Integer> todoItemDelete(@PathVariable Long itemId){
        Result<Integer> result = new Result<>();
        int delResult = todoService.deleteTodoItem(itemId);
        result.setData(delResult);
        return result;
    }

    @DeleteMapping("/delete/all")
    public Result<Integer> todoItemDeleteAll(){
        Result<Integer> result = new Result<>();
        int delResult = todoService.deleteAllTodoItem();
        result.setData(delResult);
        return result;
    }

    @PutMapping("/updateItem")
    public Result<Integer>  updateTodoItem(@RequestBody TodoItemForm form){
        Result<Integer> result = new Result<>();
        int delResult = todoService.updateItem(form);
        result.setData(delResult);
        return result;
    }

}
