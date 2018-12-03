package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.User;
import com.cityquest.quest.repository.UserRepository;
import com.cityquest.quest.utility.UsernameNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void update(String username, Answer answer){
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        User user = userRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundExeption("No user found with username " + username));
        user.setAnswers(answers);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundExeption("User " + username + " was not found. "));
    }

    @Override
    @Transactional
    public void addNewUser(User user) {
        if(userRepository.findAll().contains(user)){
            userRepository.saveAndFlush(user);
        }
    }

}
