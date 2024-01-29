package kr.letech.study.boot.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostVO {
    private String postId;		// 게시글 아이디
    private String boardId;		// 게시판 아이디
    private String userId;		// 사용자 아이디
    private String userNm;		// 사용자 이름
    private String postTtl;		// 게시글 제목
    private String postCnt;		// 게시글 내용
    private String rgstDt;		// 등록 일시
    private String rgstId;		// 등록 아이디
    private String updtDt;		// 수정 일시
    private String updtId;		// 수정 아이디
//    private List<String> deleteFileIdList;	// 수정시 삭제할 파일 아이디 리스트
}
