package com.cityquest.quest.service;

import com.cityquest.quest.model.Answer;
import com.cityquest.quest.model.Question;
import com.cityquest.quest.model.Task;
import com.cityquest.quest.repository.TasksRepository;
import com.cityquest.quest.utility.exptionHandler.TaskNotFoundException;
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
    public String addTask(Task task) {
        if (task.getId().equals(task.getQuestion().getId()) & task.getId().equals(task.getAnswer().getId())) {
                tasksRepository.saveAndFlush(task);
                return "Row is added.";
        } else
            return "The fields id are not identical.";
    }

    @Override
    public void removeTask(Long id) {
        tasksRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Task> getTaskList() {
        return tasksRepository.findAll();
    }

    @Override
    @Transactional
    public Question checkAndSendTask(Answer userResponse) {
        Task currentTask = tasksRepository.findById(userResponse.getId()).orElseThrow(() ->
                new TaskNotFoundException("Task with ID " + userResponse.getId() + " was not found. "));

        if (currentTask.getAnswer().getAnswer().equals(userResponse.getAnswer())) {
            Long nextTask = currentTask.getId() + 1L;
            return tasksRepository.findById(nextTask).orElseThrow(() ->
                    new TaskNotFoundException(" Congratulations, it was been final task.")).getQuestion();
        }
        return currentTask.getQuestion();
    }
}
