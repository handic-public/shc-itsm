package com.shc.itsm.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shc.itsm.qa.model.TodoEntity;
import com.shc.itsm.qa.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	@Autowired
	private TodoRepository repository;

	public String testService() {

		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();

		repository.save(entity);

		TodoEntity savedEntity = repository.findById(entity.getId()).get();

		return savedEntity.getTitle();
	}

	/**
	 * create Qna
	 * @param entity
	 * @return
	 */
	public List<TodoEntity> create(final TodoEntity entity) {

		// validations
		validate(entity);

		repository.save(entity);

		log.info("Entity Id : {} is saved.", entity.getId());

		return repository.findByUserId(entity.getUserId());
	}

	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}

	public List<TodoEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}

	public List<TodoEntity> update(final TodoEntity entity) {

		// validations
		validate(entity);

		final Optional<TodoEntity> original = repository.findById(entity.getId());

		// lambda 구현식
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());

			repository.save(todo);
		});

		/*
		 if(original.isPresent()) {
			 final TodoEntity todo = original.get();

			 todo.setTitle(entity.getTitle());
			 todo.setDone(entity.isDone());

			 repository.save(todo);
		 }
		 */

		return retrieve(entity.getUserId());
	}

	public List<TodoEntity> delete(final TodoEntity entity) {
		// validations
		validate(entity);		

		try {
			// 2) 엔티티 삭제
			repository.delete(entity);
		} catch (Exception e) {
			// 3) 삭제 오류시 안내
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		// 4) 리스트 반환
		return retrieve(entity.getUserId());
	}

}
