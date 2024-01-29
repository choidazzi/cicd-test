/**
 * 
 */
package kr.letech.study.boot.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface FileDAO {
	void insertFile(FileVO fileVO);
    List<FileVO> getFileByPost(String postId);
    FileVO getFileById(String fileId);
    void deleteFile(FileVO fileVO);
}
