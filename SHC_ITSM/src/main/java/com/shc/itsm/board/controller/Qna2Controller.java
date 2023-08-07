package com.shc.itsm.board.controller;

import com.shc.itsm.board.dto.BoardDTO;
import com.shc.itsm.board.dto.QnaDTO;
import com.shc.itsm.board.model.BoardEntity;
import com.shc.itsm.board.model.QnaEntity;
import com.shc.itsm.board.service.BoardService;
import com.shc.itsm.common.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("qna2")
public class Qna2Controller {
	
	@Autowired
	private BoardService service;

	@GetMapping
	public ResponseEntity<?> retrieveQnaList(@AuthenticationPrincipal String emp_no) {
//		String temporaryUserId = "184137";
		// (1) 서비스 메서드의 retrieve 메서드를 사용해 Todo 리스트를 가져온다
		List<BoardEntity> entities = service.retrieve("QA", true);
		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		// (6) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// (7) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}

	@PostMapping
	public ResponseEntity<?> createQna(@AuthenticationPrincipal String emp_no, @RequestBody BoardDTO dto) {
		try {
//			String temporaryUserId = "184137";
			// (1) QnaEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);
			// (2) id를 null로 초기화한다. 생성 당시에는 id가 없어야 한다.
			entity.setBoard_id(null);
			
			// (3) 기본값셋팅
			entity.setEmp_no(emp_no);	// 직원정보 셋팅
			entity.setBoard_division("QA");	// QnA게시판
			entity.setBoard_status("01"); // 등록상태
			entity.setView(true);	// 게시글보이기
			
			// (4) 서비스를 이용해 Qna 엔티티를 생성한다.
			Optional<BoardEntity> boardEntity = service.create(entity);
			// (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
			List<BoardDTO> dtos = boardEntity.stream().map(BoardDTO::new).collect(Collectors.toList());
			// (6) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
			// (7) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			// (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	@PutMapping
	public ResponseEntity<?> updateQna(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {

//		String temporaryUserId = "184137";
		// (1) QnaEntity로 변환
		BoardEntity entity = BoardDTO.toEntity(dto);
		// (2) 임시 유저 아이디를 설정해준다. 이부분은4장 인증과 인가에서 수정 예정, 지금은 인증과 인가 기능이 없으므로 한 유저만 로그인 없이 사용 가능한 애플리케이션 셈이다.
		entity.setEmp_no(userId);
		// (3) 서비스를 이용해 Todo 엔티티를 수정한다.
		Optional<BoardEntity> entities = service.update(entity);
		// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
		List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
		// (5) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
		ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
		// (6) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userId, @RequestBody BoardDTO dto) {

		try {
//			String temporaryUserId = "184137";

			// (1) QnaEntity로 변환
			BoardEntity entity = BoardDTO.toEntity(dto);
			// (2) 임시 유저 아이디를 설정해준다. 이부분은4장 인증과 인가에서 수정 예정, 지금은 인증과 인가 기능이 없으므로 한 유저만 로그인 없이 사용 가능한 애플리케이션 셈이다.
			entity.setEmp_no(userId);
			// (3) 서비스를 이용해 Todo 엔티티를 삭제한다.
			List<BoardEntity> entities = service.delete(entity);
			// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
			List<BoardDTO> dtos = entities.stream().map(BoardDTO::new).collect(Collectors.toList());
			// (5) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(dtos).build();
			// (6) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
