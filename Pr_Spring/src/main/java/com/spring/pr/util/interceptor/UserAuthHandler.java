package com.spring.pr.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션에서 로그인 데이터를 얻은 후 확인을 해 준다.
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			//로그인을 하지 않음.
			response.sendRedirect(request.getContextPath() +"user/UserLogin");
			return false;//컨트롤러 진입을 막기 위해 false;
		}
		
		return true;	//로그인 한 사람은 통과.
	}
}
