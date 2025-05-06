package com.hbnu.b_todoapp.mapper;

import com.hbnu.b_todoapp.domain.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Select("SELECT * FROM todos")
    List<Todo> findAll();

    @Insert("INSERT INTO todos(title, completed) VALUES(#{title}, #{completed})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Todo todo);

    @Update("UPDATE todos SET title=#{title}, completed=#{completed} WHERE id=#{id}")
    void update(Todo todo);

    @Delete("DELETE FROM todos WHERE id=#{id}")
    void deleteById(Long id);
}
