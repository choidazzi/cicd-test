package kr.letech.study.boot.board.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.letech.study.boot.board.dao.FileDAO;
import kr.letech.study.boot.board.dao.PostDAO;
import kr.letech.study.boot.board.vo.FileVO;
import kr.letech.study.boot.board.vo.PostVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
	private final PostDAO postDao; 
	private final FileDAO fileDao;
	private final FileService fileService;

	@Override
	public String getNavNm(String boardId) {
		return postDao.getNavNm(boardId);
	}

	@Override
	public PostVO getPost(String postId) {
		 return postDao.getPost(postId);
	}

	@Override
//	@Transactional
	public void insertPost(String postVO, String userId, List<MultipartFile> files) {
		ObjectMapper objectMapper = new ObjectMapper();
		PostVO postVO2 = null;
		try {
			postVO2 = objectMapper.readValue(postVO, PostVO.class);
			postVO2.setUserId(userId);
	        postVO2.setRgstId(userId);
	        postVO2.setPostId(generatePostId(userId));
			postDao.insertPost(postVO2);
		String postId = postVO2.getPostId();
		if (files.get(0).getSize() != 0) {
			List<FileVO> fileVOList = fileService.uploadFile(files);
			for (FileVO fileVO : fileVOList) {
				fileVO.setRgstId(userId);
				fileVO.setPostId(postId);
				fileDao.insertFile(fileVO);
			}
			} 
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void modifyPost(String postVO, List<MultipartFile> files, String updtId) {
		ObjectMapper objectMapper = new ObjectMapper();
		PostVO postVO2 = null;
		try {
			postVO2 = objectMapper.readValue(postVO, PostVO.class);
			postVO2.setUpdtId(updtId);
			// 게시글 수정 
			postDao.modifyPost(postVO2);
			if (files != null) {
				// 파일 수정 - 파일 등록
				List<FileVO> fileList = fileService.uploadFile(files);
				for (FileVO fileVO : fileList) {
					fileVO.setUpdtId(updtId);
					fileVO.setPostId(postVO2.getPostId());
					fileService.insertFile(fileVO);
				}	
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}

	@Override
	public void deletePost(String postId, String updtId) {
		postDao.deletePost(postId, updtId);
	}
	
	@Override
    public String getCurrentTime() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); 
        return formatter.format(date);
    }

    public String generatePostId(String userId) {
        //등록날짜-seq(000001)-등록자id
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String postId = formatter.format(date) + "-" + postDao.getPostSeq() + "-" +userId;
        return postId;
    }
}
