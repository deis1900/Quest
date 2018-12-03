package com.cityquest.quest.controller;


import com.cityquest.quest.model.Task;
import com.cityquest.quest.service.TaskService;
import com.cityquest.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adminPanel")
public class AdminController {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public AdminController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping(name = "/tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getTasks () {
        return new ResponseEntity<>(taskService.getTaskList(), HttpStatus.OK);
    }

    @PostMapping(name = "/addTask", produces = "application/json")
    public ResponseEntity<String> addNewTask(Task task) {
            taskService.addTask(task);
        return new ResponseEntity<String>("New task is added.", HttpStatus.OK);
    }


}
