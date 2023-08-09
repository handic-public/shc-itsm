package com.shc.itsm.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shc.itsm.model.User2Entity;

@Repository
public interface User2Repository extends JpaRepository<User2Entity, String>{
	User2Entity findByempNo(String empno);
	Boolean existsByempNo(String empno);
	User2Entity findByempNoAndPassword(String empno, String password);
}
