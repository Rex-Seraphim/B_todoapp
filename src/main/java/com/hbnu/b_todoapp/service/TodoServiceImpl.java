package com.hbnu.b_todoapp.service;

import com.hbnu.b_todoapp.domain.Todo;
import com.hbnu.b_todoapp.mapper.TodoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Resource
    private TodoMapper todoMapper;

    @Override
    public List<Todo> getAllTodos() {
        return todoMapper.findAll();
    }

    @Override
    public Todo createTodo(Todo todo) {
        todoMapper.insert(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        updatedTodo.setId(id);
        todoMapper.update(updatedTodo);
        return updatedTodo;
    }

    @Override
    public void deleteTodo(Long id) {
        todoMapper.deleteById(id);
    }
}
