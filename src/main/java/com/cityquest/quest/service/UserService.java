package com.cityquest.quest.service;

import com.cityquest.quest.model.User;

import java.util.List;

public interface UserService {

    void update (User user);

    void delete (Long userID);

    User findByUsername(String username);

    void addNewUser(User user);

    List<User> getUserList();
}
