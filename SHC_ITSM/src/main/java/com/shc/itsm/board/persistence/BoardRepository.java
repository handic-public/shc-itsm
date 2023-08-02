package com.shc.itsm.board.persistence;

import com.shc.itsm.board.model.BoardEntity;
import com.shc.itsm.board.model.QnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String>{
	
	List<BoardEntity> findByEMNO(String Emno);
}