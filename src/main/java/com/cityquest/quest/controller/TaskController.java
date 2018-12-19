package com.cityquest.quest.controller;


import com.cityquest.quest.model.Task;
import com.cityquest.quest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> getTaskList () {
        return new ResponseEntity<>(taskService.getTaskList(), HttpStatus.OK);
    }

    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewTask(@Valid @RequestBody final Task task) {
        System.out.println("!!!!!!" + task);
        task.setId(null);
            taskService.addTask(task);
        return new ResponseEntity<>("New task is added.", HttpStatus.CREATED);
    }


}
