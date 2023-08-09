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
    private String fileId;
    private String boardId;
    private String fileName;
    private String realFileName;
    private String filePath;
    private Date reg_date;
    private Date chg_date;
}
