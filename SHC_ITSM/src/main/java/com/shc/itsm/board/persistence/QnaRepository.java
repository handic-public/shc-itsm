package com.shc.itsm.board.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shc.itsm.board.model.QnaEntity;

@Repository
public interface QnaRepository  extends JpaRepository<QnaEntity, String>{
	
	List<QnaEntity> findByUserId(String userId);
}
