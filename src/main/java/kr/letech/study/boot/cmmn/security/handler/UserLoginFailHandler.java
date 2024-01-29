package kr.letech.study.boot.cmmn.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component("userLoginFailHandler")
@Slf4j
public class UserLoginFailHandler implements AuthenticationFailureHandler {

	/**
	 * @see AuthenticationFailureHandler#onAuthenticationFailure(HttpServletRequest, HttpServletResponse, AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		log.error(exception.getMessage());

		String errCd = null;
		if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
			errCd = "fail";
		} else {
			errCd = "error";
		}

		response.sendRedirect("/login?code=" + errCd);
	}

}
