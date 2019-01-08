package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Assumption;
import com.cityquest.quest.model.User;

import java.util.List;

public interface UserService {

    void update (User user);

    void delete (Long userID);

    User findByUsername(String username);

    void addNewUser(User user);

    List<User> getUserList();

    Assumption searchAssumptionDuplicate(User user, Answer answer);
}
