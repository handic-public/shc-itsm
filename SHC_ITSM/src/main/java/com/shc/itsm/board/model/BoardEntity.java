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
    private String BOARD_ID;
    private Integer BOARD_RN;
    private String EMNO;
    private String TITLE;
    private String CONTENT;
    private String BOARD_STATUS;
    private String BOARD_DIVSION;
    private boolean VIEW_YN;
    private String REPLY_TARGET_ID;
    private Date REG_DATE;
    private Date CHG_DATE;
}
