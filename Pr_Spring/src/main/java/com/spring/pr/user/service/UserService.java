package com.spring.pr.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.pr.command.UserVO;
import com.spring.pr.user.mapper.IUserMapper;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserMapper mapper;

	@Override
	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		return mapper.idCheck(userId);
	}

	@Override
	public void join(UserVO user) {
		// TODO Auto-generated method stub
		mapper.join(user);
	}

	@Override
	public UserVO login(String id, String pw) {
		UserVO vo = mapper.login(id, pw);
		System.out.println("mapper가 가져온 login 데이터:" + vo);
		return vo;
	}

	@Override
	public UserVO getInfo(String userId) {
		// TODO Auto-generated method stub
		return mapper.getInfo(userId);
	}

	@Override
	public void updateUser(UserVO user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(String id, String pw) {
		// TODO Auto-generated method stub

	}

}
