/**
 * 
 */
package kr.letech.study.boot.cmmn.security.service;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.letech.study.boot.cmmn.security.dao.UserDao;
import kr.letech.study.boot.cmmn.security.vo.RoleVO;
import kr.letech.study.boot.cmmn.security.vo.UserAuthVO;
import kr.letech.study.boot.cmmn.security.vo.UserDetailsVO;
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
@Service("userLoginService")
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserDetailsService{
	private final UserDao userDao;
	private final PasswordEncoder passwordEncoder;
	
	public List<RoleVO> getAllAuth() {
		return userDao.getAllAuth();
	}

	public void signIn(UserVO userVO, List<String> authId) {
		UserAuthVO userAuthVO = new UserAuthVO();
		userVO.setUserPw(passwordEncoder.encode(userVO.getUserPw()));
		userDao.signInUser(userVO);

		userAuthVO.setUserId(userVO.getUserId());
		for (String auth : authId) {
			userAuthVO.setRoleId(auth);
			userDao.signInAuth(userAuthVO);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserDetailsVO userDetailsVO = new UserDetailsVO();

		UserVO userVO = userDao.getUserInfo(userId);
		if (userVO == null) {
			throw new UsernameNotFoundException("login failed");
		} else {
			userDetailsVO.setUsername(userVO.getUserId());
			userDetailsVO.setPassword(userVO.getUserPw());
			userDetailsVO.setAuthorities(userDao.getUserAuth(userId));
		}

		return userDetailsVO;
	}

}
