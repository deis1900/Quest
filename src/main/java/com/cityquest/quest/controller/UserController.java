package com.cityquest.quest.controller;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Question;
import com.cityquest.quest.model.User;
import com.cityquest.quest.service.TaskService;
import com.cityquest.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{username}")
public class UserController {

    private final
    UserService userService;

    private final
    TaskService taskService;

    @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping(name ="/question")
    public ResponseEntity<Question> getQuestion(@PathVariable String username) {
        User user = userService.findUser(username);
        if (username.equals(user.getUsername())) {
            return new ResponseEntity<>(user.getCurrentQuestion(), HttpStatus.OK);
        }
        return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    @PostMapping(name = "/answer", produces = "application/json")
    public ResponseEntity<Question> checkAnswer(@PathVariable String username,  Answer answer) {
        userService.update(username, answer);
        return new ResponseEntity<>(taskService.checkAndSendTask(answer), HttpStatus.OK);
    }
}
