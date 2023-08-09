package com.shc.itsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shc.itsm.common.dto.ResponseDTO;
import com.shc.itsm.dto.User2DTO;
import com.shc.itsm.dto.UserDTO;
import com.shc.itsm.model.User2Entity;
import com.shc.itsm.model.UserEntity;
import com.shc.itsm.security.TokenProvider;
import com.shc.itsm.service.User2Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/auth2")
public class User2Controller {
	
	@Autowired
	private User2Service user2Service;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody User2DTO userDTO) {
		try {
			if(userDTO == null || userDTO.getPassword() == null) {
				throw new RuntimeException("Invalid Password value.");
			}
			
			User2Entity user = User2Entity.builder()
					.empNo(userDTO.getEmpNo())
					.empName(userDTO.getEmpName())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.build();
			
			User2Entity registeredUser = user2Service.create(user);
			User2DTO responseUserDTO = User2DTO.builder()
					.id(registeredUser.getId())
					.empNo(registeredUser.getEmpNo())
					.empName(registeredUser.getEmpName())
					.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
			
		} catch (Exception e) {
			// TODO: 유저 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
			
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody User2DTO user2DTO) {
		User2Entity user = user2Service.getBycreadentials(user2DTO.getEmpNo(), user2DTO.getPassword(), passwordEncoder);
		
		if( user != null) {
			final String token = tokenProvider.create(user);
			final User2DTO responseUser2DTO = User2DTO.builder()
					.empNo(user.getEmpNo())
					.id(user.getId())
					.token(token)
					.build();
			return ResponseEntity.ok().body(responseUser2DTO);
		} else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("Login failed.")
					.build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
}
