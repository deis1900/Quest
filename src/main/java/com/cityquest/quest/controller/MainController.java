package com.cityquest.quest.controller;

import com.cityquest.quest.model.User;
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
@RequestMapping("/")
public class MainController {

    private final
    UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello.", HttpStatus.OK);
    }

    @GetMapping(name = "/{name}", produces = "application/json")
    public ResponseEntity<User> infoUser(@PathVariable("name") String username){
        User user = userService.findUser(username);
        if (username.equals(user.getUsername())){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PostMapping(name = "/addNewUser", produces = "application/json")
    public ResponseEntity<String> addNewUser(User user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}