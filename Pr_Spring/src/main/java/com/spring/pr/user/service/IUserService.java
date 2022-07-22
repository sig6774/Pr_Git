package com.spring.pr.user.service;

import org.apache.ibatis.annotations.Param;

import com.spring.pr.command.UserVO;

public interface IUserService {

	// id체크
	int idCheck(String userId);
	
	// 회원가입
	void join(UserVO user);
	
	// 로그인
	UserVO login(@Param("id") String id, @Param("pw") String pw);
	
	//  정보가져오기
	UserVO getInfo(String userId);
	
	// 수정 
	void updateUser(UserVO user);
	
	// 탈퇴 
	void deleteUser(@Param("id") String id, @Param("pw") String pw);
}
