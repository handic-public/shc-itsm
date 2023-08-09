package com.shc.itsm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shc.itsm.model.User2Entity;
import com.shc.itsm.model.UserEntity;
import com.shc.itsm.persistence.User2Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class User2Service {
	
	@Autowired
	private User2Repository user2Repository;
	
	public User2Entity create(final User2Entity user2Entity) {
		
		if(user2Entity == null || user2Entity.getEmpNo() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		
		final String empno = user2Entity.getEmpNo();
		if(user2Repository.existsByempNo(empno)) {
			log.warn("EmpNo already exists {}", empno);
			throw new RuntimeException("EmpNo already exists");
		}
		
		return user2Repository.save(user2Entity);
	}
	
	public User2Entity getBycreadentials(final String empno, final String password, final PasswordEncoder encoder) {
		final User2Entity orginalUser = user2Repository.findByempNo(empno);
		
		// mathces 메서드를 이요해 패스워드가 같은지 확인
		if(orginalUser != null && encoder.matches(password,  orginalUser.getPassword())) {
			return orginalUser;
		}
		
		return null;
//		return userRepository.findByUsernameAndPassword(username, password);
	}
}
