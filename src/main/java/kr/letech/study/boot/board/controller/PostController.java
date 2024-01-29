/**
 *
 */
package kr.letech.study.boot.board.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.letech.study.boot.board.service.FileService;
import kr.letech.study.boot.board.service.PostService;
import kr.letech.study.boot.board.vo.FileVO;
import kr.letech.study.boot.board.vo.PostVO;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *
 * </pre>
 *
 * << 개정이력 >>
 *
 *  수정일      수정자		수정내용
 *  ------------------------------------------------
 *  2023-12-19  CSY			최초 생성
 */

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    /**
     * 게시글 등록
     * @param postVO    등록할 게시물 정보
     * @param files        등록할 파일 정보
     * @param principal
     */
    @PostMapping("/api/v1/posts")
    public ResponseEntity<Void> registerPost(@RequestParam("postVO") String postVO,
                                             @RequestParam("files") List<MultipartFile> files,
                                             Principal principal) {
        String userId = principal.getName();
        postService.insertPost(postVO, userId, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 게시글 상세 정보
     * @param postId    조회하려는 게시글 아이디
     * @return 게시물 상세 정보
     */
    @GetMapping("/api/v1/posts/{postId}")
    public ResponseEntity<Map<String, Object>> detailPost(@PathVariable String postId) {
        Map<String, Object> postMap = new HashMap<>();
        List<FileVO> files = fileService.getFileByPost(postId);
        PostVO postInfo = postService.getPost(postId);
        postMap.put("postInfo", postInfo);
        postMap.put("fileInfo", files);
        return ResponseEntity.ok(postMap);
    }

    /**
     * 게시물 미리보기
     * @param fileId
     */
    @GetMapping("/api/v1/files/{fileId}")
    public void preview(@PathVariable String fileId, HttpServletResponse response) {
        FileVO fileVO = fileService.getFileById(fileId);
        fileService.preview(fileVO, response);
    }

    /**
     * 게시글 수정
     * @param postVO    수정할 게시글 내용
     * @param files        추가 업로드 파일
     */
    @PutMapping("/api/v1/posts")
    public ResponseEntity<Void> modifyPost(@RequestParam("postVO") String postVO,
                                           @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                           Principal principal) {
        String updtId = principal.getName();
        postService.modifyPost(postVO, files, updtId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 게시글 삭제
     * @param postId    삭제할 게시글 아이디
     */
    @DeleteMapping("/api/v1/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable String postId, Principal principal) {
        postService.deletePost(postId, principal.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/api/v1/posts/{fileId}/download")
    public ResponseEntity<Void> fileDownload(@PathVariable String fileId, HttpServletResponse response) {
    	fileService.fileDownload(fileId, response);
    	return new ResponseEntity<>(HttpStatus.OK);
    }

}
