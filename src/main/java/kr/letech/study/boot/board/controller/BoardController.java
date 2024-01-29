/**
 * 
 */
package kr.letech.study.boot.board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import kr.letech.study.boot.board.service.BoardService;
import kr.letech.study.boot.board.service.FileService;
import kr.letech.study.boot.board.service.PostService;
import kr.letech.study.boot.board.vo.BoardVO;
import kr.letech.study.boot.board.vo.PostVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 
 * </pre>
 *  
 * << 개정이력 >>
 *   
 *  수정일      수정자		수정내용
 *  ------------------------------------------------
 *  2023-12-18  CSY			최초 생성
 */
@RestController
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	/**
	 * 게시판에 따른 게시물 목록
	 * @param boardId	게시판 아이디
	 * @param pageNum	게시판 페이지 번호 
	 * @param pageSize	게시판 페이지 사이즈
	 * @return			게시글 목록
	 */
	@GetMapping("/api/v1/boards")
	public ResponseEntity<PageInfo<PostVO>> getBoard(@RequestParam(defaultValue = "1") int pageNum,
													 @RequestParam(defaultValue = "10") int pageSize,
													 BoardVO boardVO) {
		PageInfo<PostVO> pageInfo = boardService.getAllPostByBoard(boardVO, pageNum, pageSize);
		return ResponseEntity.ok(pageInfo);
	}
	
	/**
	 * 게시판 등록
	 * @param boardNm	등록하려는 게시판 이름
	 */
	@PostMapping("/boards")
	public ResponseEntity<Void> insertBoard(String boardNm, Principal principal) {
		boardService.insertBoard(boardNm, principal.getName());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * 게시판 수정
	 * @param boardVO	수정하려는 게시판 정보(아이디, 이름)
	 */
	@PutMapping("/board/modifyBoard")
	public ResponseEntity<Void> modifyBoard(BoardVO boardVO, Principal principal) {
		boardService.modifyBoard(boardVO, principal.getName());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * 게시판 삭제
	 * @param id	삭제하려는 게시판 아이디
	 */
	@DeleteMapping("/board/deleteBoard")
	public ResponseEntity<Void> modifyBoard(String id, Principal principal) {
		boardService.deleteBoard(id, principal.getName());
		//NO_CONTENT(204): response body 존재 X, 삭제 요청으로 자원을 참조하는 모든 자원도 삭제되었을 때  
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
