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
	 * //이메일 인증
	 * 
	 * @GetMapping("/mailCheck")
	 * 
	 * @ResponseBody public String mailCheck(String email) { //파라미터명과 던져준 값의 이름이 같을
	 * 경우 @RequestBody생략 가능 System.out.println("이메일 인증 요청 들어옴");
	 * System.out.println("인증 이메일:" + email); return mailService.joinEmail(email); }
	 */
		

	//회원 가입 처리
	@PostMapping("/UserJoin")
	public String join(UserVO vo, RedirectAttributes ra) {
		System.out.println("param: " + vo);
		vo.setTotalUserTel();
		vo.setTotalUserEmail();
		vo.setTotalUserAddr();
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess");
		System.out.println("로그인성공!");
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
		System.out.println("여기로 넘어오나?");
		return "/user/userLogin";
	}
	
	@GetMapping("/userMypage")
	public void userMyPage(HttpSession session, Model model) {
		System.out.println("마이페이지 이동 요청이 들어옴.");

		//세션데이터에서 id를 뽑아야 sql문을 돌릴 수 있음.
		
		//.(참조연산자)가 더 우선순위가 높기 때문에 형변환을 더 먼저해야 하므로 괄호를 한 번 더 감싸줌
		String id = ((UserVO)session.getAttribute("login")).getUserId();
		
		UserVO vo = service.getInfo(id);
		System.out.println("JOIN의 결과: " +vo);
		model.addAttribute("userInfo", vo);
		
	}
	
	
}
