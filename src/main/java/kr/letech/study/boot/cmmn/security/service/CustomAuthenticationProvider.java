/**
 * 
 */
package kr.letech.study.boot.cmmn.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.letech.study.boot.cmmn.security.vo.UserDetailsVO;
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
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final PasswordEncoder passwordEncoder;
	private final UserLoginServiceImpl userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String userPw = authentication.getCredentials().toString();
		
		UserDetailsVO userDetailsVO = (UserDetailsVO) userDetailsService.loadUserByUsername(userId);
		if (!passwordEncoder.matches(userPw, userDetailsVO.getPassword())) {
			throw new BadCredentialsException("비밀번호가 잘못 되었습니다.");
		} 
		
		if (!userDetailsVO.isEnabled()) { 
			throw new BadCredentialsException("해당 계정은 존재하지 않습니다.");
		}
		
		return new UsernamePasswordAuthenticationToken(userId, userPw, userDetailsVO.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}