package com.shc.itsm.board.service;

import com.shc.itsm.board.model.BoardEntity;
import com.shc.itsm.board.persistence.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    /**
     * select Qna
     * @return List
     */
    public List<BoardEntity> retrieve(String Division, Boolean isView) {
        return boardRepository.findByBoard_divisionAndView(Division, isView);
    }

    /**
     * QnA에 대해 게시글 작성
     * 공통 입력값 체크
     * @param entity
     * @return List
     */
    public Optional<BoardEntity> create(final BoardEntity entity) {

        // validations
        validate(entity);

        // DB Insert
        BoardEntity postEntity = boardRepository.save(entity);

        log.info("Entity Id : {} is saved.", entity.getBoard_id());

        // 등록후 조회
        return boardRepository.findById(postEntity.getBoard_id());
    }

    /**
     * 게시글 작성
     * 공통 입력값 체크
     * @param entity
     * @return List
     */
    public Optional<BoardEntity> update(final BoardEntity entity) {

        // validations
        validate(entity);

        // 기존데이터 조회
        final Optional<BoardEntity> original = boardRepository.findById(entity.getBoard_id());

        // lambda 구현식
        original.ifPresent(board -> {

            board.setTitle(entity.getTitle());
            board.setContent(entity.getContent());
            board.setView(entity.isView());
            board.setBoard_status(entity.getBoard_status());

            boardRepository.save(board);
        });

        log.info("Entity Id : {} is saved.", entity.getBoard_id());

        // 등록후 조회
        return boardRepository.findById(entity.getBoard_id());
    }

    /**
     * delete Qna
     * @param entity
     * @return List
     */
    public List<BoardEntity> delete(final BoardEntity entity) {
        // validations
        validate(entity);

        try {
            // 2) 엔티티 삭제
            boardRepository.delete(entity);
        } catch (Exception e) {
            // 3) 삭제 오류시 안내
            log.error("error deleting entity ", entity.getBoard_id(), e);
            throw new RuntimeException("error deleting entity " + entity.getBoard_id());
        }
        // 4) 리스트 반환
        return retrieve("", true);
    }

    /**
     * validate
     * 공통 입력값 체크
     * @param entity
     * @return void
     */
    private void validate(final BoardEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getEmp_no() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
