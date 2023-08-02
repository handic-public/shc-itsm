package com.shc.itsm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USER_TB")
public class User2Entity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String EMNO;
    private String PWD;
    private String EMP_DIV_CODE;
    private Date LAST_PWD_CHG_DATE;
    private Integer LOGIN_FAIL_COUNT;
    private String SESSION_ID;
    private boolean USE_YN;
    private Date REG_DATE;
    private Date CHG_DATE;
}
