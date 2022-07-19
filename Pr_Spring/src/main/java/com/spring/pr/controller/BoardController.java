package com.spring.pr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.pr.board.service.IBoardService;
import com.spring.pr.command.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService service;
	
	//게시글 이동
	@GetMapping("/boardRegist")
	public String regist() {
		return "/board/boardRegist";
	}
	
	//게시글 등록
	@PostMapping("/boardRegist")
	public String regist(BoardVO board, RedirectAttributes ra) {
		service.regist(board);
		ra.addFlashAttribute("msg", "정상 등록 처리 되었습니다.");
		
		return "redirect:/board/boardList";
	}
	
	//게시물 목록 요청
	@GetMapping("/boardList")
	public String listBoard(Model model) {
		
		model.addAttribute("bList" , service.getList());
		return "/board/boardList";
	}
	
	//게시물 상세 보기
	@GetMapping("/boardDetail/{BNum}")
	public String Detail(@PathVariable int BNum, Model model) {
		model.addAttribute("board", service.getContent(BNum));
		return "/board/boardDetail";
	}
	
	//게시글 수정 창
	@GetMapping("/boardModify")
//	public String moveModify(@PathVariable int BNum,  Model model) {
	public String moveModify(BoardVO vo, Model model) {
		System.out.println("수정 요청 파라미터 가져오는지 확인 : " + vo.getBNum());
		
		BoardVO board = service.getContent(vo.getBNum());
		model.addAttribute("board", board);
		return "/board/boardModify";
	}
	
	@PostMapping("/boardModify")
	public String Modify(BoardVO board, RedirectAttributes ra) {
		service.update(board);
		ra.addFlashAttribute("msg", "수정이 완료 되었습니다.");
		
		return "redirect:/board/boardDetail/" + board.getBNum();
	}
}














