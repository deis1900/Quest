package com.cityquest.quest.service;

import com.cityquest.quest.model.Assumption;
import com.cityquest.quest.model.User;

import java.util.List;

public interface UserService {

    void update (String username, Long currentIssue, Assumption assumption);

    User findUser(String username);

    void addNewUser(User user);

    List<User> getUserList();
}
