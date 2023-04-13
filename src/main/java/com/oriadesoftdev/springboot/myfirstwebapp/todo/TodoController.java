package com.oriadesoftdev.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "list-todos", method = RequestMethod.GET)
    public String listAllTodos(ModelMap modelMap) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoService.findByUsername(username);
        modelMap.addAttribute("todos", todos);
        return "listTodos";
    }

    private String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showTodo(ModelMap modelMap) {
        Todo todo = new Todo(0, getLoggedInUsername(), "",
                LocalDate.now().plusYears(1), false);
        modelMap.put("todo", todo);
        return "addTodo";
    }


    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors())
            return "addTodo";
        todoService.addTodo(getLoggedInUsername(), todo.getDescription(),
                todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap modelMap) {
        Todo todo = todoService.findTodoById(id);
        modelMap.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors())
            return "addTodo";
        todo.setUsername(getLoggedInUsername());
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

}
