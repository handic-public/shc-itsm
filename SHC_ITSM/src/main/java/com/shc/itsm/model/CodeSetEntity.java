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
@Table(name = "CODE_SET_TB")
public class CodeSetEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String value_cd;
    private String value_nm;
    private String large_cd;
    private String middle_cd;
    private String detail_cd;
    private String large_nm;
    private String middle_nm;
    private String detail_nm;
    private boolean use;
    private Integer sort_seq;
    private Date reg_date;
    private Date chg_date;
}
