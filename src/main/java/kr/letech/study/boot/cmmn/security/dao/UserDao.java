/**
 * 
 */
package kr.letech.study.boot.cmmn.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.letech.study.boot.cmmn.security.vo.RoleVO;
import kr.letech.study.boot.cmmn.security.vo.UserAuthVO;
import kr.letech.study.boot.cmmn.security.vo.UserVO;

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
@Mapper
public interface UserDao {
    UserVO getUserInfo(String userId);
    List<String> getUserAuth(String userId);
    List<RoleVO> getAllAuth();
    void signInUser(UserVO userVO);
    void signInAuth(UserAuthVO userAuthVO);
}
