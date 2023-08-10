package com.shc.itsm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USER_TB", uniqueConstraints = {@UniqueConstraint(columnNames = "empNo")})
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; 
    @Column(nullable = false)
    private String empNo;			// 직원번호(직원명의 경우 swing사용해볼예정)
    private String empName;			// 임시작성
    private String password;		// null 가능함(SSO 인증인 swing이 가능할경우)
    private String role;			// 권한(0관리자, 1일반)
    private String authProvider;	// OAuth에서 사용할 유저 정보 제공자
    private Date lastPwdChgDate;	// 사용할지 모르겠지만 일단 일단 넣고 (3개월 변경주기 체크)
    private Integer loginFailCount;	// 사용자 잠금을 위함
    private String sessionId;		// 쓰일지는 모르지만 세션id 저장(세션 초기화 정도도 필요할듯)
    private boolean use;			// 계정사용여부 체크
    private Date regDate;			// 계정등록일자
    private Date chgDate;			// 최종변경일자(최초등일일자는 필요할까 싶다.)
}
