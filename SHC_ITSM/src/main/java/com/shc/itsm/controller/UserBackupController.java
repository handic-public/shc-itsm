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
import com.shc.itsm.dto.UserBackupDTO;
import com.shc.itsm.model.UserBackupEntity;
import com.shc.itsm.security.TokenProvider;
import com.shc.itsm.service.UserBackupService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/authBackup")
public class UserBackupController {
	
	@Autowired
	private UserBackupService userService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserBackupDTO userDTO) {
		try {
			if(userDTO == null || userDTO.getPassword() == null) {
				throw new RuntimeException("Invalid Password value.");
			}
			
			UserBackupEntity user = UserBackupEntity.builder()
					.username(userDTO.getUsername())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.build();
			
			UserBackupEntity registeredUser = userService.create(user);
			UserBackupDTO responseUserDTO = UserBackupDTO.builder()
					.id(registeredUser.getId())
					.username(registeredUser.getUsername())
					.build();
			
			return ResponseEntity.ok().body(responseUserDTO);
			
		} catch (Exception e) {
			// TODO: 유저 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
			
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserBackupDTO userDTO) {
		UserBackupEntity user = userService.getBycreadentials(userDTO.getUsername(), userDTO.getPassword(), passwordEncoder);
		
		if( user != null) {
//			final String token = tokenProvider.create(user);
			final UserBackupDTO responseUserDTO = UserBackupDTO.builder()
					.username(user.getUsername())
					.id(user.getId())
//					.token(token)
					.build();
			return ResponseEntity.ok().body(responseUserDTO);
		} else {
			ResponseDTO responseDTO = ResponseDTO.builder()
					.error("Login failed.")
					.build();
			return ResponseEntity.badRequest().body(responseDTO);
		}
	}
}
