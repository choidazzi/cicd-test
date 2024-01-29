/**
 * 
 */
package kr.letech.study.boot.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import kr.letech.study.boot.board.vo.FileVO;

/**
 * <pre>
 * 
 * </pre>
 *  
 * << 개정이력 >>
 *   
 *  수정일      수정자		수정내용
 *  ------------------------------------------------
 *  2023-12-14  CSY			최초 생성
 */
public interface FileService {
	List<FileVO> uploadFile(List<MultipartFile> files);
	String getFolder();
	String getExtension(String fileOrgNm);
	void fileDownload(String fileId, HttpServletResponse response);
	void preview(FileVO fileVO, HttpServletResponse response);
	
	FileVO getFileById(String fileId);
	List<FileVO> getFileByPost(String postId);
	void insertFile(FileVO fileVO);
	void deleteFIle(FileVO fileVO);
}
