package com.taskbackend.taskbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskbackend.taskbackend.model.Task;
import com.taskbackend.taskbackend.repo.TaskRepo;
import com.taskbackend.taskbackend.utils.DateUtils;
import com.taskbackend.taskbackend.utils.ValidationException;

@RestController
@RequestMapping(value ="/tasks")
public class TaskController {

	@Autowired
	private TaskRepo tasksRepo;
	
	@GetMapping
	public List<Task> findAll() {
		return tasksRepo.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Task> save(@RequestBody Task tasks) throws ValidationException {
		if(tasks.getTask() == null || tasks.getTask() == "") {
			throw new ValidationException("Fill the task description");
		}
		if(tasks.getDueDate() == null) {
			throw new ValidationException("Fill the due date");
		}
		if(!DateUtils.isEqualOrFutureDate(tasks.getDueDate())) {
			throw new ValidationException("Due date must not be in past");
		}
		Task saved = tasksRepo.save(tasks);
		return new ResponseEntity<Task>(saved, HttpStatus.CREATED);
	}
}