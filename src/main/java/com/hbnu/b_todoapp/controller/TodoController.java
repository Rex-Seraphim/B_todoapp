package com.hbnu.b_todoapp.controller;

import com.hbnu.b_todoapp.domain.Todo;
import com.hbnu.b_todoapp.service.TodoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Resource
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }


    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo){
        return todoService.updateTodo(id, updatedTodo);
    }


    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }


}
