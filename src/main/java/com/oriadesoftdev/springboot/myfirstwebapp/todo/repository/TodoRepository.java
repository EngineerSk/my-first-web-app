package com.oriadesoftdev.springboot.myfirstwebapp.todo.repository;

import com.oriadesoftdev.springboot.myfirstwebapp.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    public List<Todo> findByUsername(String username);
}
