package com.hjy.controller.backend;

import com.alibaba.fastjson.JSONObject;
import com.hjy.common.Const;
import com.hjy.common.ResponseCode;
import com.hjy.common.ServerResponse;
import com.hjy.entity.Admin;
import com.hjy.service.AdminService;
import com.hjy.util.RedisOperator;
import com.hjy.util.SHA256Util;
import org.apache.catalina.connector.Request;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hjy
 * @create 2018/07/01
 **/
@RestController
@RequestMapping("/console/")
public class AdminController {

	private Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private RedisOperator redis;

	@PostMapping(value = "login")
	public ServerResponse<Admin> login(@RequestBody Admin admin,
									   HttpSession session,
									   HttpServletRequest request,
									   HttpServletResponse response) {
		String adminname = admin.getAdminname();
		String password = admin.getPassword();

		ServerResponse<Admin> serverResponse = adminService.login(adminname,password);

		if(serverResponse.isSuccess()){
			// 登录成功  redis保存sessionId加密后的信息
			String key = SHA256Util.getSHA256StrJava(session.getId() + adminname);
			redis.set(key, JSONObject.toJSONString(serverResponse.getData().getAdminId()));
		} else {
			// 登录失败
			return serverResponse;
		}

		// 设置请求头：解决关闭浏览器session就被干掉的问题
		Cookie cookie  = new Cookie("JSESSIONID", session.getId());
		//cookie.setSecure(true);
		cookie.setHttpOnly(true);
		//cookie.setDomain(".luneice.com");
		cookie.setPath(request.getContextPath() + "/");
		cookie.setMaxAge(30*60);
		response.addCookie(cookie);

		return serverResponse;
	}


	@PostMapping(value = "register")
	public ServerResponse register(@RequestBody Admin admin) {
		String adminname = admin.getAdminname();
		String password = admin.getPassword();

		// 校验关键信息是否为空
		if (StringUtils.isBlank(adminname) || StringUtils.isBlank(password)) {
			return ServerResponse.createByErrorMessage(ResponseCode.BAD_REQUEST.getDesc());
		}

		return adminService.register(admin);
	}

	@PostMapping(value = "check")
	public ServerResponse check(@RequestBody Admin admin) {
		String adminname = admin.getAdminname();

		return  adminService.checkValid(adminname, Const.ADMINNAME);
	}


}
