package com.spring.pr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.pr.board.service.IBoardService;
import com.spring.pr.command.BoardVO;

import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService service;
	
	//게시물 화면 요청
	@GetMapping("/boardRegist")
	public void regist() {}
	
	
	//게시물 등록 요청
	@PostMapping("/boardRegist")
	public String regist(BoardVO board, RedirectAttributes ra) {
		service.regist(board);
		ra.addFlashAttribute("msg", "정상 등록 처리되었습니다.");
		
		return "redirect:/board/boardList";
		
	}
	
	//게시물 목록 요청
	@GetMapping("/boardList")
	public void getList(Model model) {
		
		List<BoardVO> listboard = service.getList();
		model.addAttribute("bList", listboard);
	}
	
	//게시물 상세보기 화면
	@GetMapping("/boardDetail/{BNum}")
	public String detail(@PathVariable int BNum, Model model) {
		
		model.addAttribute("board", service.getContent(BNum));
		
		return "/board/boardDetail";
	}
	
	//게시물 수정 화면 요청
	@GetMapping("/boardModify")
	public String modify(BoardVO vo, Model model) {
		
		System.out.println("게시물 수정 화면 GET: " + vo.getBNum());
		model.addAttribute("board", service.getContent(vo.getBNum()));
		return "/board/boardModify";
	}
	
	@PostMapping("/boardModify")
	public String modify(BoardVO board, RedirectAttributes ra) {
		System.out.println("수정 게시글 가져오는지 확인 : " + board);
		BoardVO upboard = service.getContent(board.getBNum());
		upboard.setBTitle(board.getBTitle());
		upboard.setBContent(board.getBContent());
		// 게시글 수정 
		System.out.println("수정 확인  : " + upboard);
		service.update(upboard);
		
		ra.addFlashAttribute("msg", "수정이 완료 되었습니다.");

		return "redirect:/board/boardList";
	}
	
	
	//게시물 삭제 요청
	@GetMapping("/boardDelete/{BNum}")
	public String Delete(@PathVariable int BNum, RedirectAttributes ra) {
		System.out.println("삭제 요청 파라미터 가져오는지 확인: " + BNum);
		
		service.delete(BNum);
		ra.addAttribute("msg", "삭제가 완료되었습니다.");
		return "redirect:/board/boardList";
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
