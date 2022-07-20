package com.spring.pr.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.pr.command.UserVO;
import com.spring.pr.user.service.IUserService;
/*import com.spring.pr.util.MailSendService;*/

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	/*
	 * @Autowired private MailSendService mailService;
	 */
	
	@GetMapping("/userJoin")
	public String join() {
		return "/user/userJoin";
	}
	
	/*
	 * //�̸��� ����
	 * 
	 * @GetMapping("/mailCheck")
	 * 
	 * @ResponseBody public String mailCheck(String email) { //�Ķ���͸�� ������ ���� �̸��� ����
	 * ��� @RequestBody���� ���� System.out.println("�̸��� ���� ��û ����");
	 * System.out.println("���� �̸���:" + email); return mailService.joinEmail(email); }
	 */
		

	//ȸ�� ���� ó��
	@PostMapping("/UserJoin")
	public String join(UserVO vo, RedirectAttributes ra) {
		System.out.println("param: " + vo);
		vo.setTotalUserTel();
		vo.setTotalUserEmail();
		vo.setTotalUserAddr();
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess");
		System.out.println("�α��μ���!");
		return "redirect:/user/userLogin";
	}
	
	@GetMapping("/userLogin")
	public String login() {
		return "/user/userLogin";
	}
	
	@PostMapping("/UserLogin")
	public String login(String userId, String userPw, Model model) {
		model.addAttribute("user", service.login(userId, userPw));
		System.out.println("userId:" + userId);
		System.out.println("userPw:" + userPw);
		System.out.println("����� �Ѿ����?");
		return "/user/userLogin";
	}
	
	@GetMapping("/userMypage")
	public void userMyPage(HttpSession session, Model model) {
		System.out.println("���������� �̵� ��û�� ����.");

		//���ǵ����Ϳ��� id�� �̾ƾ� sql���� ���� �� ����.
		
		//.(����������)�� �� �켱������ ���� ������ ����ȯ�� �� �����ؾ� �ϹǷ� ��ȣ�� �� �� �� ������
		String id = ((UserVO)session.getAttribute("login")).getUserId();
		
		UserVO vo = service.getInfo(id);
		System.out.println("JOIN�� ���: " +vo);
		model.addAttribute("userInfo", vo);
		
	}
	
	
}
