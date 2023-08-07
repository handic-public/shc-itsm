package com.shc.itsm.board.controller;

import com.shc.itsm.board.dto.BoardDTO;
import com.shc.itsm.board.dto.QnaDTO;
import com.shc.itsm.board.model.BoardEntity;
import com.shc.itsm.board.model.QnaEntity;
import com.shc.itsm.board.service.QnaService;
import com.shc.itsm.common.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("qna")
public class Qna2Controller {
	
	@Autowired
	private QnaService service;
	
	@PostMapping
	public ResponseEntity<?> createQna(@AuthenticationPrincipal String emp_id, @RequestBody BoardDTO dto) {
		try {
//			String temporaryUserId = "184137";
			// (1) QnaEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);
			// (2) id를 null로 초기화한다. 생성 당시에는 id가 없어야 한다.
			entity.setBoard_id(null);
			
			// (3) 기본값셋팅
			entity.setEmp_no(emp_id);
			entity.setBoard_division("QA");	// QnA게시판
			entity.setBoard_status("01"); // 등록상태
			entity.setView(true);	// 게시글보이기
			
			// (4) 서비스를 이용해 Qna 엔티티를 생성한다.
			Optional<BoardEntity> boardEntity = service.post(entity);
			// (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
			List<BoardDTO> dtos = boardEntity.stream().map(BoardDTO::new).collect(Collectors.toList());
			// (6) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
			// (7) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			// (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
