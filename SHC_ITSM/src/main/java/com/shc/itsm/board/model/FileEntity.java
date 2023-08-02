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
@Table(name = "FILE_TB")
public class FileEntity {

    @Id
    @GeneratedValue(generator ="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String FILE_ID;
    private String BOARD_ID;
    private String FILE_NAME;
    private String REAL_FILE_NAME;
    private String FILE_PATH;
    private Date REG_DATE;
    private Date CHG_DATE;
}
