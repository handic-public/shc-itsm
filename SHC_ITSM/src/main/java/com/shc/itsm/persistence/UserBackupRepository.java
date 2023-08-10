package com.shc.itsm.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shc.itsm.model.UserBackupEntity;

@Repository
public interface UserBackupRepository extends JpaRepository<UserBackupEntity, String>{
	UserBackupEntity findByUsername(String username);
	Boolean existsByUsername(String username);
	UserBackupEntity findByUsernameAndPassword(String username, String password);
}
