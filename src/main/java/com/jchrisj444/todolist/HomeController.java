package com.jchrisj444.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoItemRepository todoItemRepository;

    @RequestMapping("/")
    public String listTodoItems (Model model){
        model.addAttribute("todos", todoItemRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String todoitemForm(Model model){
        model.addAttribute("todoitem", new Todoitem());
        return "todoitemform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Todoitem todoitem,
        BindingResult result){
        if (result.hasErrors()){
            return "todoitemform";
        }
        todoItemRepository.save(todoitem);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showTodoItem(@PathVariable("id") long id, Model model){
        model.addAttribute("todoitem", todoItemRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateTodoItem(@PathVariable("id") long id,
                               Model model){
        model.addAttribute("todoitem", todoItemRepository.findById(id).get());
        return "todoitemform";
    }

    @RequestMapping("/delete/{id}")
    public String delTodoItem(@PathVariable("id") long id){
        todoItemRepository.deleteById(id);
        return "redirect:/";
    }

}


