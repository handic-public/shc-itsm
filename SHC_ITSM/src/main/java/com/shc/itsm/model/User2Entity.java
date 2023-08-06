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
@Table(name = "USER_TB")
public class User2Entity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String emp_no;
    private String password;
    private String emp_div_code;
    private Date last_pwd_chg_date;
    private Integer login_fail_count;
    private String session_id;
    private boolean use;
    private Date reg_date;
    private Date chg_date;
}
