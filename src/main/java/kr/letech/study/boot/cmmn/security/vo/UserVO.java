package kr.letech.study.boot.cmmn.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
    private String userId;	// 사용자 아이디
    private String userPw;	// 사용자 비밀번호
    private String userNm;	// 사용자 이름
    private String rgstDt;	// 등록 일시
    private String rgstId;	// 등록 아이디
    private String updtDt;	// 수정 일시
    private String updtId;	// 수정 아이디
    private char delYn;		// 삭제 여부 
}
