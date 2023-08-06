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
	private String board_id;
	private Integer sort_seq;
	private String title;
	private String content;
	private String board_status;
	private String board_division;
	private boolean view;
	private String reply_target_id;

	public BoardDTO(final BoardEntity entity) {
		this.board_id = entity.getBoard_id();
		this.sort_seq = entity.getSort_seq();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.board_status = entity.getBoard_status();
		this.board_division = entity.getBoard_division();
		this.view = entity.isView();
		this.reply_target_id = entity.getReply_target_id();
	}
	
	public static BoardEntity toEntity(final BoardDTO dto) {
		return BoardEntity.builder()
				.board_id(dto.getBoard_id())
				.sort_seq(dto.getSort_seq())
				.title(dto.getTitle())
				.content(dto.getContent())
				.board_status(dto.getBoard_status())
				.board_division(dto.getBoard_division())
				.reply_target_id(dto.getReply_target_id())
				.view(dto.isView())
				.build();
	}
}
