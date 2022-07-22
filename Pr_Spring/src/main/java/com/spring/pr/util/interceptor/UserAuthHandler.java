package com.spring.pr.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class UserAuthHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//���ǿ��� �α��� �����͸� ���� �� Ȯ���� �� �ش�.
		System.out.println("userLoginAuth");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			//�α����� ���� ����.
			response.sendRedirect(request.getContextPath() +"user/UserLogin");
			return false;//��Ʈ�ѷ� ������ ���� ���� false;
		}
		
		return true;	//�α��� �� ����� ���.
	}
}
