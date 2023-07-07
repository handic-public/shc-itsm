package com.shc.itsm.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shc.itsm.board.dto.QnaDTO;
import com.shc.itsm.board.model.QnaEntity;
import com.shc.itsm.board.service.QnaService;
import com.shc.itsm.common.dto.ResponseDTO;

@RestController
@RequestMapping("qna")
public class QnaController {
	
	@Autowired
	private QnaService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService();
		
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody QnaDTO dto) {
		try {
			String temporaryUserId = "184137";
			// (1) QnaEntity로 변환
			QnaEntity entity = QnaDTO.toEntity(dto);
			// (2) id를 null로 초기화한다. 생성 당시에는 id가 없어야 한다.
			entity.setSeq(null);
			// (3) 임시 유저 아이디를 설정해준다. 이부분은4장 인증과 인가에서 수정 예정, 지금은 인증과 인가 기능이 없으므로 한 유저만 로그인 없이 사용 가능한 애플리케이션 셈이다.
			entity.setUserId(temporaryUserId);
			// (4) 서비스를 이용해 Qna 엔티티를 생성한다.
			List<QnaEntity> entities = service.create(entity);
			// (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
			List<QnaDTO> dtos = entities.stream().map(QnaDTO::new).collect(Collectors.toList());
			// (6) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
			ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().data(dtos).build();
			// (7) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			// (8) 혹시 예외가 나는 경우 dto대신 error에 메시지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retrieveTodoList() {
		String temporaryUserId = "184137";
		// (1) 서비스 메서드의 retrieve 메서드를 사용해 Todo 리스트를 가져온다
		List<QnaEntity> entities = service.retrieve(temporaryUserId);
		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
		List<QnaDTO> dtos = entities.stream().map(QnaDTO::new).collect(Collectors.toList());
		// (6) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
		ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().data(dtos).build();			
		// (7) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody QnaDTO dto) {
		
		String temporaryUserId = "184137";
		// (1) QnaEntity로 변환
		QnaEntity entity = QnaDTO.toEntity(dto);
		// (2) 임시 유저 아이디를 설정해준다. 이부분은4장 인증과 인가에서 수정 예정, 지금은 인증과 인가 기능이 없으므로 한 유저만 로그인 없이 사용 가능한 애플리케이션 셈이다.
		entity.setUserId(temporaryUserId);
		// (3) 서비스를 이용해 Todo 엔티티를 수정한다.
		List<QnaEntity> entities = service.update(entity);
		// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
		List<QnaDTO> dtos = entities.stream().map(QnaDTO::new).collect(Collectors.toList());
		// (5) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
		ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().data(dtos).build();			
		// (6) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody QnaDTO dto) {
		
		try {
			String temporaryUserId = "184137";
			
			// (1) QnaEntity로 변환
			QnaEntity entity = QnaDTO.toEntity(dto);
			// (2) 임시 유저 아이디를 설정해준다. 이부분은4장 인증과 인가에서 수정 예정, 지금은 인증과 인가 기능이 없으므로 한 유저만 로그인 없이 사용 가능한 애플리케이션 셈이다.
			entity.setUserId(temporaryUserId);
			// (3) 서비스를 이용해 Todo 엔티티를 삭제한다.
			List<QnaEntity> entities = service.delete(entity);
			// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 QnaDTO 리스트로 변환한다.
			List<QnaDTO> dtos = entities.stream().map(QnaDTO::new).collect(Collectors.toList());
			// (5) 변환된 QnaDTO 리스트를 이용해 ResponseDTO를 초기화 한다.
			ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().data(dtos).build();			
			// (6) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<QnaDTO> response = ResponseDTO.<QnaDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
