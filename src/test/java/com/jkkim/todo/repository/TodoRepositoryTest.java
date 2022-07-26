package com.jkkim.todo.repository;

import com.jkkim.todo.domain.ItemSearch;
import com.jkkim.todo.domain.QTodoItem;
import com.jkkim.todo.domain.TodoItem;
import com.jkkim.todo.domain.TodoItemDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TodoRepositoryTest {

    @Autowired TodoRepository todoRepository;
    
    @BeforeEach
    public void eachSave(){
        List<TodoItem> all = new ArrayList<>();
        TodoItem item = TodoItem.createItem("item test1");
        all.add(item);
        TodoItem item2 = TodoItem.createItem("item test2");
        all.add(item2);
        TodoItem item3 = TodoItem.createItem("item test3");
        all.add(item3);
        TodoItem item4 = TodoItem.createItem("item test4");
        all.add(item4);
        TodoItem item5 = TodoItem.createItem("item test5");
        all.add(item5);
        TodoItem item6 = TodoItem.createItem("item test6");
        all.add(item6);
        todoRepository.saveAll(all);
    }

    @Test
    @Rollback(value = false)
    public void 할일목록저장(){
        //given
        TodoItem item = TodoItem.createItem("item test");

        //when
        TodoItem saveItem = todoRepository.save(item);

        //then
        TodoItem findItemId = todoRepository.findById(saveItem.getId()).get();
        assertThat(saveItem.getId()).isEqualTo(findItemId.getId());
    }

    @Test
    public void 할일목록변경() throws Exception{
        //given
        TodoItem item = TodoItem.createItem("item test");
        TodoItem saveItem = todoRepository.save(item);

        //when
        TodoItem findItem = todoRepository.findById(saveItem.getId()).get();

        TodoItemDto dto = new TodoItemDto(Long.parseLong("1"),"item test2222",false);
        findItem.modifyItem(dto);

        //then
        TodoItem findItem2 = todoRepository.findById(saveItem.getId()).get();
        assertThat(findItem2.getName()).isEqualTo("item test2222");


    }

    @Test
    @Rollback(value = false)
    public void 할일목록() throws Exception{
        //given
        ItemSearch is = new ItemSearch();
        is.setName("item");

        //when
        List<TodoItem> result = new ArrayList<>();
        QTodoItem item = QTodoItem.todoItem;
        Iterable<TodoItem> all = todoRepository.findAll(item.name.contains("item"));
        all.forEach(c -> {
            result.add(c);
        });
        //then
        assertThat(result.size()).isEqualTo(6);



    }

}