package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Question;
import com.cityquest.quest.model.Task;
import com.cityquest.quest.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final
    TasksRepository tasksRepository;

    @Autowired
    public TaskServiceImpl(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        if(getTaskList().contains(task)){
            tasksRepository.saveAndFlush(task);
        }
    }

    @Override
    @Transactional
    public List<Task> getTaskList() {
        return tasksRepository.findAll();
    }

    @Override
    @Transactional
    public Question checkAndSendTask(Answer userResponse) {
        List<Task> tasks = tasksRepository.findAll();
        int currentQuestionID = userResponse.getId().intValue();
        Task currentTask = tasks.get(currentQuestionID);

        if (currentTask.getAnswer().equals(userResponse)){
            return tasks.get(currentQuestionID + 1).getQuestion();
        }
        return tasks.get(currentQuestionID).getQuestion();
    }


}
