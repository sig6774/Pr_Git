package com.spring.pr.user.mapper;

import org.apache.ibatis.annotations.Param;

import com.spring.pr.command.UserVO;

public interface IUserMapper {

	// ���̵��ߺ�Ȯ��
	int idCheck(String userId);
	
	//��й�ȣ Ȯ��
	int pwCheck(String userPw);
	
	// ȸ������
	void join(UserVO user);
	
	// �α���
	UserVO login(@Param("id") String id, @Param("pw") String pw);
	
	// ���������� ������������
	UserVO getInfo(String userId);
	
	// ��������
	void updateUser(UserVO user);
	
	// ȸ�� Ż��
	void deleteUser(@Param("id") String id, @Param("pw") String pw);
}
