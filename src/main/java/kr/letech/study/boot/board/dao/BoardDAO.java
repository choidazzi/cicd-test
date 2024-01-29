package kr.letech.study.boot.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study.boot.board.vo.BoardVO;
import kr.letech.study.boot.board.vo.PostVO;

@Mapper
public interface BoardDAO {
    /* select */
    List<BoardVO> getNavItems();
    List<PostVO> getAllPostByBoard(BoardVO boardVO);
    String getBoardSeq();
    
    /* update */
    void modifyBoard(BoardVO boardVO);
    void deleteBoard(BoardVO boardVO);
    
    /* insert */
    void insertBoard(BoardVO boardVO);
}
