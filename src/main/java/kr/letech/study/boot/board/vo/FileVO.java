package kr.letech.study.boot.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class FileVO {
    private String fileId;		// 파일 아이디
    private String postId;		// 게시글 아이디 
    private String fileOrgNm;	// 파일 원래 이름
    private String fileSaveNm;	// 파일 저장 이름
    private long fileSize;		// 파일 사이즈
    private String filePath;	// 파일 저장 경로
    private String rgstDt;		// 등록 일시
    private String rgstId;		// 등록 아이디
    private String updtDt;		// 수정 일시
    private String updtId;		// 수정 아이디
    private char delYn;			// 삭제 여부
}
