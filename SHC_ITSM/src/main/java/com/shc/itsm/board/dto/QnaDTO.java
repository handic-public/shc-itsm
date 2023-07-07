package com.shc.itsm.board.dto;

import com.shc.itsm.board.model.QnaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QnaDTO {
	private String seq;
	private String title;
	private String content;
	private String answer_seq;
	private boolean view;
	
	public QnaDTO(final QnaEntity entity) {
		this.seq = entity.getSeq();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.answer_seq = entity.getAnswer_seq();
		this.view = entity.isView();
	}
	
	public static QnaEntity toEntity(final QnaDTO dto) {
		return QnaEntity.builder()
				.seq(dto.getSeq())
				.title(dto.getTitle())
				.content(dto.getContent())
				.answer_seq(dto.getAnswer_seq())
				.view(dto.isView())
				.build();
	}
}
