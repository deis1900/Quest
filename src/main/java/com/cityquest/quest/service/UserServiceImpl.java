package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Assumption;
import com.cityquest.quest.model.User;
import com.cityquest.quest.repository.UserRepository;
import com.cityquest.quest.utility.UsernameNotFoundExeption;
import com.cityquest.quest.utility.exptionHandler.UserIdMismatchException;
import com.cityquest.quest.utility.exptionHandler.UsernameAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TaskService taskService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskService taskService) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void delete(Long userID) {
        if (!userRepository.findById(userID).isPresent())
            throw new UserIdMismatchException("User with id " + userID + " isn't exist!");
        System.out.println(userRepository.findById(userID) + "  " + userRepository.findById(userID).isPresent());
        userRepository.deleteById(userID);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundExeption("User " + username + " was not found. "));
    }

    @Override
    @Transactional
    public void addNewUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent())
                throw new UsernameAlreadyExistException("Username " + user.getUsername() + " is already exist");
            user.setCurrentIssue(1L);
            Long taskId = user.getCurrentIssue();
            user.setCurrentQuestion(taskService.getTask(taskId).getQuestion());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public Assumption searchAssumptionDuplicate(User user, Answer answer){
        Assumption assumption = new Assumption();
        if (user.getAssumptions().isEmpty()) {
            assumption.setAssumption(answer.getAnswer());
        }
        else {
            user.getAssumptions().forEach(as -> {
                if (!as.getAssumption().equals(answer.getAnswer())) assumption.setAssumption(answer.getAnswer());
            });
        }
        if (assumption.getAssumption().isEmpty()) return null;
        return assumption;
    }

}
