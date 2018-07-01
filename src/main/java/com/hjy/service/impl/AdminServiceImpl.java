package com.hjy.service.impl;

import com.hjy.common.ServerResponse;
import com.hjy.entity.Admin;
import com.hjy.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hjy
 * @create 2018/07/01
 **/
@Service("AdminService")
public class AdminServiceImpl implements AdminService {


	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ServerResponse<Admin> login(String username, String password) {
		return null;
	}
}
