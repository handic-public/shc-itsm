package com.shc.itsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shc.itsm.model.UserBackupEntity;
import com.shc.itsm.persistence.UserBackupRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserBackupService {
	
	@Autowired
	private UserBackupRepository userRepository;
	
	public UserBackupEntity create(final UserBackupEntity userEntity) {
		
		if(userEntity == null || userEntity.getUsername() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String username = userEntity.getUsername();
		if(userRepository.existsByUsername(username)) {
			log.warn("Username already exists {}", username);
			throw new RuntimeException("Username already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserBackupEntity getBycreadentials(final String username, final String password, final PasswordEncoder encoder) {
		final UserBackupEntity orginalUser = userRepository.findByUsername(username);
		
		// mathces 메서드를 이요해 패스워드가 같은지 확인
		if(orginalUser != null && encoder.matches(password,  orginalUser.getPassword())) {
			return orginalUser;
		}
		
		return null;
//		return userRepository.findByUsernameAndPassword(username, password);
	}
}
