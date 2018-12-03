package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.User;

public interface UserService {

    void update (String username, Answer answer);

    User findUser(String username);

    void addNewUser(User user);
}
