/**
 * 
 */
package kr.letech.study.boot.cmmn.security.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.letech.study.boot.cmmn.security.service.CustomAuthenticationProvider;
import kr.letech.study.boot.cmmn.security.service.UserLoginServiceImpl;
import kr.letech.study.boot.cmmn.security.vo.UserVO;
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
public class LoginController {

    private final CustomAuthenticationProvider authenticationProvider;
    private final UserLoginServiceImpl userLoginServiceImpl;

    @PostMapping("/api/v1/users/login")
    public ResponseEntity<Void> login(@RequestParam String userId, @RequestParam String userPw) throws AuthenticationException {
        // 인증을 위한 토큰 생성
		Authentication authentication = new UsernamePasswordAuthenticationToken(userId, userPw);
		Authentication result = authenticationProvider.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(result);
		return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/api/v1/users/signIn")
    public ResponseEntity<Void> signIn(UserVO userVO, @RequestParam List<String> authIdList) {
    	userLoginServiceImpl.signIn(userVO, authIdList);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
}