package com.hjy.controller.backend;

import com.hjy.common.ServerResponse;
import com.hjy.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author hjy
 * @create 2018/07/01
 **/
@RestController
@RequestMapping("/console/")
public class AdminController {

	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	private AdminService adminService;


	@PostMapping(value = "login")
	public ServerResponse login(String username, String password, HttpSession session) {
		ServerResponse response = adminService.login(username,password);
		return null;
	}





}
