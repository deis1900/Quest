package com.cityquest.quest.controller;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Assumption;
import com.cityquest.quest.model.Question;
import com.cityquest.quest.model.User;
import com.cityquest.quest.service.TaskService;
import com.cityquest.quest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
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

    @GetMapping(value = "/{username}/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(@PathVariable final String username) {
        User user = userService.findUser(username);
        if (username.equals(user.getUsername())) {
            return new ResponseEntity<>(user.getCurrentQuestion(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/{username}/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> checkAnswer(@PathVariable final String username,
                                                @Valid @RequestBody final Answer answer) {
        Question question = taskService.checkAndSendTask(answer);
        Assumption assumption = new Assumption();
        assumption.setAssumption(answer.getAnswer());
        userService.update(username, question.getId(), assumption);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUserList () {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewUser(@RequestBody final User user) {
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{username}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> infoUser(@PathVariable final String username) {
        User user = userService.findUser(username);
        System.out.println(user);
        if (username.equals(user.getUsername())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

}
