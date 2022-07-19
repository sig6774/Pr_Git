package com.spring.pr.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
	
	private int pageNum;
	private int countPerPage;
	
	public PageVO() {
		this.pageNum = 1;
		this.countPerPage = 10;
	}

}
