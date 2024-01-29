package kr.letech.study.boot.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.letech.study.boot.board.vo.PostVO;

public interface PostService {
	/* select */
	String getNavNm(String boardId);
	PostVO getPost(String postId);
	String getCurrentTime();
	
	/* insert */
	void insertPost(String postVO, String userId, List<MultipartFile> files);
	
    /* update */
    void modifyPost(String postVO, List<MultipartFile> files, String userId);
    void deletePost(String postId, String updtId);
}

