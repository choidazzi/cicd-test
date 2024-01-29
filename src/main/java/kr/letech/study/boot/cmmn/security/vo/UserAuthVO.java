package kr.letech.study.boot.cmmn.security.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserAuthVO {
	private String userId;		// 사용자 아이디
	private String roleId;		// 권한 아이디
    private String rgstDt;		// 등록 일시
    private String rgstId;		// 등록 아이디
    private String updtDt;		// 수정 일시
    private String updtId;		// 수정 아이디
    private char delYn;			// 삭제 여부 
}
