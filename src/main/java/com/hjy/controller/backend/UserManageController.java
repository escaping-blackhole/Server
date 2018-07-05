package com.hjy.controller.backend;

import com.hjy.common.Const;
import com.hjy.common.ResponseCode;
import com.hjy.common.ServerResponse;
import com.hjy.controller.portal.UserController;
import com.hjy.entity.User;
import com.hjy.service.UserService;
import com.hjy.util.CookieUtil;
import com.hjy.util.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonRawValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author hjy
 * @create 2018/06/07
 **/
@RestController
@RequestMapping("/console/user/")
public class UserManageController {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisOperator redis;

	private Logger logger = LoggerFactory.getLogger(UserManageController.class);


	@GetMapping(value = "list")
	public ServerResponse getList(HttpServletRequest request,
								  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
								  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

		//Cookie验证
		String key = CookieUtil.getUid(request, Const.JSESSIONID);
		String value =redis.get(key);
		if (StringUtils.isBlank(value)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

		}

		return userService.getUserList(pageNum,pageSize);
	}


	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ServerResponse queryUserById(@PathVariable("id") Long id) {

		return userService.queryUserById(id);
	}

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping("/{user_id}")
	public ServerResponse deleteUserById(@PathVariable("user_id") String ids) {
		return userService.deleteUser(ids);
	}

	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@PutMapping("{id}")
	public ServerResponse updateUser(@PathVariable("id") Long id , @RequestBody(required = false) User user) {

		if (user == null) {
			return ServerResponse.createByErrorMessage("输入参数有误！");
		}
		user.setUserId(id);
		//return ServerResponse.createByErrorMessage("输入参数有误！");
		return userService.updateUser(user);
	}





}
