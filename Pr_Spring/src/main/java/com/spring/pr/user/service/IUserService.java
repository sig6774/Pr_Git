package com.spring.pr.user.service;

import org.apache.ibatis.annotations.Param;

import com.spring.pr.command.UserVO;

public interface IUserService {

	// idüũ
	int idCheck(String userId);
	
	/*
	 * //pwüũ int pwCheck(String userPw);
	 */
	
	// ȸ������
	void join(UserVO user);
	
	// �α���
	UserVO login(@Param("id") String id, @Param("pw") String pw);
	
	//  ������������
	UserVO getInfo(String userId);
	
	// ���� 
	void updateUser(UserVO user);
	
	// Ż�� 
	void deleteUser(@Param("id") String id, @Param("pw") String pw);
}
