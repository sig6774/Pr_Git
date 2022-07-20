package com.spring.pr.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.pr.command.UserVO;
import com.spring.pr.user.mapper.IUserMapper;

public class UserLoginSuccessHandler implements HandlerInterceptor{
	
	@Autowired
	private IUserMapper mapper;
	
	
	//��Ʈ�ѷ� ���� ���Ŀ� ����Ǵ� �ڵ鷯(post Handler) �������̵�.
	//login ��û���� ���� �� ����ǵ��� xml ���Ͽ� ������ ��� �� ����.
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("�α��� ���ͼ��� ����.");
		/* modelAndView.getModel().get("key"); �� ����� ����(o) */
		ModelMap mv = modelAndView.getModelMap();
		UserVO vo = (UserVO)mv.get("user");
		
		System.out.println("interceptor vo: " + vo );
		
		if(vo != null) {	//��Ʈ�ѷ����� �α����� �����ߴ� ���.
			System.out.println("�α��� ���� ���� ����.");
			//�α��� ������ ȸ������ ���� �����͸� �����ؼ� �α��� ������ �ϰ� �� ��.
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);
			//������Ʈ�� �̾ƿ��� �޼���: getContextPath(); -->url�Ľ��� �� �����.
			response.sendRedirect(request.getContextPath());
			System.out.println("�α��� �����ߴµ�");
		}else { //vo == null->�α��� ����.
			modelAndView.addObject("msg", "loginFail");
			modelAndView.setViewName("/user/userLogin");
			System.out.println("�α��� ���������ϱ� �� �Ѿ");
		}
	}
}
