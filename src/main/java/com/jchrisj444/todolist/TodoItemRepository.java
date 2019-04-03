package com.jchrisj444.todolist;

import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<Todoitem, Long> {
}
