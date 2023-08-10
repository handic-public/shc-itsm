package com.shc.itsm.board.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name= "board_id")
    private String boardId;
    @Column(name= "sort_seq")
    private Integer sortSeq;
    @Column(name= "user_id")
    private String userId;
    private String title;
    private String content;
    @Column(name= "board_status")
    private String boardStatus;
    @Column(name= "board_division")
    private String boardDivision;
    private boolean view;
    @Column(name= "reply_target_id")
    private String replyTargetId;
    private Date reg_date;
    private Date chg_date;
}
