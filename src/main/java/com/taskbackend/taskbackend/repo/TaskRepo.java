package com.taskbackend.taskbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskbackend.taskbackend.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

}
