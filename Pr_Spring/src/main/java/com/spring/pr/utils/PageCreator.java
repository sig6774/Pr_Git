package com.spring.pr.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator {
	
	private PageVO paging;
	private int totalArticle;
	
	private int endPage;
	private int beginPage;
	
	private boolean previous;
	private boolean next;
	
	private final int buttonNum = 5;
	
	private void calPage() {
		this.endPage = (int) Math.ceil(paging.getPageNum() / (double) this.buttonNum) * this.buttonNum;
		
		this.beginPage = (this.endPage - this.buttonNum) + 1;
		this.previous = (this.beginPage == 1) ? false : true;
		
		this.next = this.totalArticle <= (this.endPage * this.paging.getCountPerPage()) ? false:true;
		
		if(!this.next) {
			this.endPage = (int) Math.ceil(this.totalArticle / (double) this.paging.getCountPerPage());
		}
	}
	
	public void settotalArticle(int totalcount) {
		
		this.totalArticle = totalcount;
		
		calPage();
	}
	
	public String makeURI(int page) {
		UriComponents ucp = UriComponentsBuilder.newInstance().queryParam("pageNum", page)
															  .queryParam("countPerPage", paging.getCountPerPage())
//															  .queryParam("keyword", paging.getKeyword())
//															  .queryParam("condition", paging.getCondition())
															  .build();
		// uri를 컨트롤러에서 받은 searchVO객체인 paging에서 받아서 구성 
		return ucp.toUriString();
		
				
	}
	
	

}
