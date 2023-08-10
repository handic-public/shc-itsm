package com.shc.itsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shc.itsm.model.UserEntity;
import com.shc.itsm.model.UserBackupEntity;
import com.shc.itsm.persistence.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		
		if(userEntity == null || userEntity.getEmpNo() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String empno = userEntity.getEmpNo();
		if(userRepository.existsByempNo(empno)) {
			log.warn("EmpNo already exists {}", empno);
			throw new RuntimeException("EmpNo already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity getBycreadentials(final String empno, final String password, final PasswordEncoder encoder) {
		final UserEntity orginalUser = userRepository.findByempNo(empno);
		
		// mathces 메서드를 이요해 패스워드가 같은지 확인
		if(orginalUser != null && encoder.matches(password,  orginalUser.getPassword())) {
			return orginalUser;
		}
		
		return null;
//		return userRepository.findByUsernameAndPassword(username, password);
	}
}
