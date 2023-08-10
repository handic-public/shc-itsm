package com.shc.itsm.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shc.itsm.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
	UserEntity findByempNo(String empno);
	Boolean existsByempNo(String empno);
	UserEntity findByempNoAndPassword(String empno, String password);
}
