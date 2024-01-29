package kr.letech.study.boot.board.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import kr.letech.study.boot.board.vo.BoardVO;
import kr.letech.study.boot.board.vo.PostVO;

public interface BoardService {
    /* select */
    List<BoardVO> getNavItems();
    PageInfo<PostVO> getAllPostByBoard(BoardVO boardVO, int pageNum, int pageSize);
    
    /* update */
    void modifyBoard(BoardVO boardVO, String userId);
    void deleteBoard(String boardId, String userId);
    
    /* insert */
    void insertBoard(String boardNm, String userId);
}
