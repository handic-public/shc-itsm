package com.shc.itsm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User2DTO {
	private String token;
	private String id;
	private String empNo;
	private String empName;
	private String password;
	private String sessionId;
}
