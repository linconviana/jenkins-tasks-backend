package com.taskbackend.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taskbackend.taskbackend.model.Task;
import com.taskbackend.taskbackend.repo.TaskRepo;
import com.taskbackend.taskbackend.utils.ValidationException;

@ExtendWith(SpringExtension.class)
public class TaskControllerTest {

	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	Task todo = new Task();
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		try {	
			/// :: Não poderia passar a descrição
			/// :: todo.setTask("Descricao");
			
			todo.setDueDate(LocalDate.now());
			controller.save(todo);
			
			Assertions.fail("Não deveria chaegar nesnte ponto");
		}
		catch (ValidationException e) {
			Assertions.assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		try {		
			todo.setTask("Descricao");
			/// :: todo.setDueDate(LocalDate.now());
			controller.save(todo);
			
			Assertions.fail("Não deveria chaegar nesnte ponto");
		}
		catch (ValidationException e) {
			Assertions.assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		try {		
			todo.setTask("Descricao");
			todo.setDueDate(LocalDate.of(2010,01,01));
			controller.save(todo);
			
			Assertions.fail("Não deveria chaegar nesnte ponto");
		}
		catch (ValidationException e) {
			Assertions.assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		
		/// :: Testar metodo externo
		Mockito.verify(taskRepo).save(todo);
	}
}
