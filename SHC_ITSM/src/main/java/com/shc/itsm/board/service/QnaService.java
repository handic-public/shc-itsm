package com.shc.itsm.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shc.itsm.board.model.QnaEntity;
import com.shc.itsm.board.persistence.QnaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QnaService {
	
	@Autowired
	private QnaRepository repository;

	/**
	 * select Qna
	 * @param entity
	 * @return List
	 */
	public List<QnaEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
	
	/**
	 * insert Qna
	 * @param entity
	 * @return List
	 */
	public List<QnaEntity> create(final QnaEntity entity) {

		// validations
		validate(entity);

		repository.save(entity);

		log.info("Entity Id : {} is saved.", entity.getSeq());
		
		// 등록한 사용자 
		return repository.findByUserId(entity.getUserId());
	}
	
	/**
	 * update Qna
	 * @param entity
	 * @return List
	 */
	public List<QnaEntity> update(final QnaEntity entity) {

		// validations
		validate(entity);

		final Optional<QnaEntity> original = repository.findById(entity.getSeq());

		// lambda 구현식
		original.ifPresent(Qna -> {
			Qna.setTitle(entity.getTitle());
			Qna.setContent(entity.getContent());
			Qna.setView(entity.isView());

			repository.save(Qna);
		});

		return retrieve(entity.getUserId());
	}

	/**
	 * delete Qna
	 * @param entity
	 * @return List
	 */
	public List<QnaEntity> delete(final QnaEntity entity) {
		// validations
		validate(entity);		

		try {
			// 2) 엔티티 삭제
			repository.delete(entity);
		} catch (Exception e) {
			// 3) 삭제 오류시 안내
			log.error("error deleting entity ", entity.getSeq(), e);
			throw new RuntimeException("error deleting entity " + entity.getSeq());
		}
		// 4) 리스트 반환
		return retrieve(entity.getUserId());
	}
	
	/**
	 * validate
	 * 공통 입력값 체크
	 * @param entity
	 * @return void
	 */
	private void validate(final QnaEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
}
