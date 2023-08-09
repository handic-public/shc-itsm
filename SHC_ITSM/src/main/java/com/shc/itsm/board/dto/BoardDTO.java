package com.shc.itsm.board.dto;

import com.shc.itsm.board.model.BoardEntity;
import com.shc.itsm.board.model.QnaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	private String boardId;
	private Integer sortSeq;
	private String userId;
	private String empNo;
	private String title;
	private String content;
	private String boardStatus;
	private String boardDivision;
	private boolean view;
	private String replyTargetId;

	public BoardDTO(final BoardEntity entity) {
		this.boardId = entity.getBoardId();
		this.sortSeq = entity.getSortSeq();
		this.userId = entity.getUserId();
		this.empNo = entity.getEmpNo(); 
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.boardStatus = entity.getBoardStatus();
		this.boardDivision = entity.getBoardDivision();
		this.view = entity.isView();
		this.replyTargetId = entity.getReplyTargetId();
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.boardId(dto.getBoardId())
				.sortSeq(dto.getSortSeq())
				.userId(dto.getUserId())
				.empNo(dto.getEmpNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.boardStatus(dto.getBoardStatus())
				.boardDivision(dto.getBoardDivision())
				.replyTargetId(dto.getReplyTargetId())
				.view(dto.isView())
				.build();
	}
}
