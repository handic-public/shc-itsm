package com.shc.itsm.board.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shc.itsm.board.model.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String>{
	
	List<BoardEntity> findByUserId(String userId);
	List<BoardEntity> findByBoardDivisionAndView(String Division, Boolean isView);
}
