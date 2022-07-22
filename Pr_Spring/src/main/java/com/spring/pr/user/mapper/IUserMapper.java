package com.spring.pr.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.pr.command.UserVO;

public interface IUserMapper {

	// 아이디중복확인
	int idCheck(String userId);
	
	//비밀번호 확인
	int pwCheck(String userPw);
	
	// 회원가입
	void join(UserVO user);
	
	// 로그인
	UserVO login(@Param("id") String id, @Param("pw") String pw);
	
	// 마이페이지 정보가져오기
	UserVO getInfo(String userId);
	
	// 정보수정
	void updateUser(UserVO user);
	
	// 회원 탈퇴
	void deleteUser(@Param("id") String id, @Param("pw") String pw);
}
