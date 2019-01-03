package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Question;
import com.cityquest.quest.model.Task;

import java.util.List;

public interface TaskService {

    String upsertTask(Task task);

    Task getTask(Long id);

    void removeTask(Long id);

    List<Task> getTaskList();

    Question checkAndSendTask(Answer answer);

}
