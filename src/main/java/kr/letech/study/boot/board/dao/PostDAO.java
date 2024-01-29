package kr.letech.study.boot.board.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study.boot.board.vo.PostVO;

@Mapper
public interface PostDAO {
	/* select */
	String getNavNm(String boardId);
    String getPostSeq();
    PostVO getPost(String postId);
    
    /* insert */
    void insertPost(PostVO postVO);

    /* update */
    void modifyPost(PostVO postVO);
    void deletePost(String postId, String updtId);
    
}
