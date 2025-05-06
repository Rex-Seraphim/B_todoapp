package com.hbnu.b_todoapp.service;

import com.hbnu.b_todoapp.dao.TodoRepository;
import com.hbnu.b_todoapp.domain.Todo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TodoServiceImpl implements TodoService {
    @Resource
    private TodoRepository todoRepository;


    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(updatedTodo.getTitle());
                    todo.setCompleted(updatedTodo.getCompleted());
                    return todoRepository.save(todo);
                }).orElseGet(() -> {
                    updatedTodo.setId(id);
                    return todoRepository.save(updatedTodo);
                });
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
