package com.shc.itsm.board.model;

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
@Table(name = "BOARD_TB")
public class BoardEntity {
    @Id
    @GeneratedValue(generator ="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String board_id;
    private Integer sort_seq;
    private String emp_no;
    private String title;
    private String content;
    private String board_status;
    private String board_division;
    private boolean view;
    private String reply_target_id;
    private Date reg_date;
    private Date chg_date;
}
