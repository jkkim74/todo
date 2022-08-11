package com.jkkim.todo.controller;

import com.jkkim.todo.domain.*;
import com.jkkim.todo.repository.TodoRepository;
import com.jkkim.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/items",method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    private final TodoRepository todoRepository;

    @Value("${paging.size}")
    private int pagingSize;


    // save
    @PutMapping("/saveItem")
    public Page<TodoItemDto> saveTodoItem(@RequestBody TodoItemForm form){
        TodoItem item = TodoItem.createItem(form.getName());
        TodoItem newItem = todoService.saveTodoItem(item);
        return getTodItemListsByPage(0);
    }

    private Page<TodoItemDto> getTodItemListsByPage(int pageNum) {
        ItemSearch itemSearch = new ItemSearch();
        PageRequest page = PageRequest.of(pageNum,pagingSize, Sort.by("regDate").descending());
        return todoRepository.findTodoItemByPage(itemSearch, page);
    }

    // list
//    @GetMapping("/list")
//    public Result<List<TodoItemDto>> todoItemList(@ModelAttribute("itemSearch") ItemSearch itemSearch, Model model){
//        Result<List<TodoItemDto>> result = new Result<>();
//        List<TodoItemDto> itemResult = new ArrayList<>();
//        List<TodoItem> todoItems = todoService.todoItemListByExcutor(itemSearch);
//        todoItems.stream().forEach(t -> {
//            TodoItemDto dto = new TodoItemDto(t.getId(),t.getName(),t.getCompleted());
//            itemResult.add(dto);
//        });
//
//        result.setData(itemResult);
//        return result;
//    }

    @GetMapping("/list")
    public  Page<TodoItemDto> todoItemList(@ModelAttribute("itemSearch") ItemSearch itemSearch, Model model){
        PageRequest page = PageRequest.of(itemSearch.getOffset(), itemSearch.getSize(), Sort.by("regDate").descending());
        return todoRepository.findTodoItemByPage(itemSearch, page);
    }

    // update

    // delete
    @DeleteMapping("/delete/{pageNum}/{itemId}")
    public Page<TodoItemDto> todoItemDelete(@PathVariable int pageNum,
                                          @PathVariable Long itemId){
        int delResult = todoService.deleteTodoItem(itemId);
        return getTodItemListsByPage(pageNum);
    }

    @DeleteMapping("/delete/all")
    public Page<TodoItemDto> todoItemDeleteAll(){
        Result<Integer> result = new Result<>();
        int delResult = todoService.deleteAllTodoItem();
        result.setData(delResult);
        return getTodItemListsByPage(0);
    }

    @PutMapping("/updateItem")
    public Result<Integer>  updateTodoItem(@RequestBody TodoItemForm form){
        Result<Integer> result = new Result<>();
        int delResult = todoService.updateItem(form);
        result.setData(delResult);
        return result;
    }

}
