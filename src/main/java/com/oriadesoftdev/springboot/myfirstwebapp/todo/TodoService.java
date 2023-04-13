package com.oriadesoftdev.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static final List<Todo> todos;

    private static int todosCount = 0;

    static {
        todos = new ArrayList<>();
        todos.add(new Todo(++todosCount, "Oriade", "Learn AWS",
                LocalDate.now().plusYears(1L), false));
        todos.add(new Todo(++todosCount, "Oriade", "Learn Cloud DevOps",
                LocalDate.now().plusYears(1L), false));
        todos.add(new Todo(++todosCount, "Oriade", "Learn Full Stack Development",
                LocalDate.now().plusYears(1L), false));

    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo>predicate = todo->todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean completed) {
        todos.add(new Todo(++todosCount, username, description, targetDate, completed));
    }

    public void deleteTodoById(int id) {
        int i = 0, j = todos.size() - 1;
        while (i <= j) {
            if (todos.get(i).getId() == id) {
                --todosCount;
                todos.remove(i);
                break;
            }
            ++i;
        }
    }

    public void updateTodo(@Valid Todo todo) {
        int i = 0, j = todos.size() - 1;
        while (i <= j) {
            if (todos.get(i).getId() == todo.getId()) {
                todos.remove(i);
                todos.add(i, todo);
                break;
            }
            ++i;
        }
    }

    public Todo findTodoById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        List<Todo> todoList = todos.stream().filter(predicate).toList();
        if (!todoList.isEmpty())
            return todoList.get(0);
        return null;
    }
}
