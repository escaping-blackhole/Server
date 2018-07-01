package com.hjy.service;

import com.hjy.common.ServerResponse;
import com.hjy.entity.Admin;

/**
 * @author hjy
 * @create 2018/07/01
 **/
public interface AdminService {

	ServerResponse<Admin> login(String username, String password);

}
