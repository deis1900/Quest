package com.cityquest.quest.controller;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Assumption;
import com.cityquest.quest.model.Question;
import com.cityquest.quest.model.User;
import com.cityquest.quest.service.TaskService;
import com.cityquest.quest.service.UserService;
import com.cityquest.quest.utility.exptionHandler.TaskIdMismatchException;
import com.cityquest.quest.utility.exptionHandler.UserIdMismatchException;
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
        User user = userService.findByUsername(username);
        if (username.equals(user.getUsername())) {
            return new ResponseEntity<>(user.getCurrentQuestion(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/{username}/task", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> checkAnswer(@PathVariable final String username,
                                                @Valid @RequestBody final Answer answer) {

        User user = userService.findByUsername(username);
        if (user.getCurrentIssue().equals(answer.getId())) {
            Question question = taskService.checkAndSendTask(answer);
            Assumption assumption = userService.searchAssumptionDuplicate(user, answer);
            if (assumption != null) user.addAssumptions(assumption);
            user.setCurrentIssue(question.getId());
            userService.update(user);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        throw new TaskIdMismatchException("Id answer isn't match to the id current question");
    }

    @GetMapping(value = "/{username}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> infoUser(@PathVariable final String username) {
        User user = userService.findByUsername(username);
        if (username.equals(user.getUsername())) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUserList() {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewUser(@Valid @RequestBody final User user) {
        userService.addNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@PathVariable final String userId) {
        userService.delete(Long.parseLong(userId));
        return new ResponseEntity<>("User with id " + userId + " was deleted", HttpStatus.OK);
    }

}
