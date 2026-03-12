package com.acme.todolist.adapters.rest_api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;

/**
 * Le controlleur Spring MVC qui expose les endpoints REST
 * 
 * @author bflorat
 *
 */
@RestController
public class TodoListController {
	
	
	private GetTodoItems getTodoItemsQuery;
	private AddTodoItem addTodoItemsQuery;
	
	
	@Inject
	public TodoListController(GetTodoItems getTodoItemsQuery, AddTodoItem addTodoItem ) {
		this.getTodoItemsQuery = getTodoItemsQuery;
		this.addTodoItemsQuery= addTodoItem;
	}
	
	@GetMapping("/todos")
	public List<TodoItem> getAllTodoItems() {
		return this.getTodoItemsQuery.getAllTodoItems();
	}
	
	
	// Endpoint de type POST vers "/todos"
    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED) // renvoie un 201 Created
    public void ajouterItem(@RequestBody TodoItem item) {
        addTodoItemsQuery.addTodoItem(item);
    }
	
	
}
