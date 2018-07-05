package com.hjy.controller.backend;

import com.hjy.common.Const;
import com.hjy.common.ResponseCode;
import com.hjy.common.ServerResponse;
import com.hjy.entity.Course;
import com.hjy.service.CourseService;
import com.hjy.util.CookieUtil;
import com.hjy.util.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;



/**
 * @author hjy
 * @create 2018/06/24
 **/
@RestController
@RequestMapping("/console/course/")
public class CourseManageController {

	private Logger logger = LoggerFactory.getLogger(CourseManageController.class);

	@Autowired
	private CourseService courseService;

	@Autowired
	private RedisOperator redis;


	@GetMapping(value = "list")
	public ServerResponse getList(HttpServletRequest  request,
								  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
								  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
								  @RequestParam(value = "majorName",defaultValue = "") String majorName,
								  @RequestParam(value = "courseName",defaultValue = "") String courseName){
		//Cookie验证
		String key = CookieUtil.getUid(request, Const.JSESSIONID);
		String value =redis.get(key);
		if (StringUtils.isBlank(value)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

		}

		//判断权限
		if (Integer.parseInt(value) > Const.JUDGEROLE) {
			return ServerResponse.createByErrorMessage("权限不够");
		}

		return courseService.getCourseList(pageNum,pageSize,majorName,courseName);

	}
	@DeleteMapping("/{courseIds}")
	public ServerResponse deleteCourseByCourseId(HttpServletRequest  request,
									@PathVariable("courseIds")String courseIds) {
		//Cookie验证
		String key = CookieUtil.getUid(request, Const.JSESSIONID);
		String value =redis.get(key);
		if (StringUtils.isBlank(value)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

		}

		//判断权限
		if (Integer.parseInt(value) > Const.JUDGEROLE) {
			return ServerResponse.createByErrorMessage("权限不够");
		}

		return  courseService.deleteCourseById(courseIds);
	}


	@PutMapping("{courseId}")
	public ServerResponse updateCourse(HttpServletRequest  request,
									   @PathVariable("courseId")String courseId,
									   @RequestBody Course course) {
		//Cookie验证
		String key = CookieUtil.getUid(request, Const.JSESSIONID);
		String value =redis.get(key);
		if (StringUtils.isBlank(value)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

		}

		//判断权限
		if (Integer.parseInt(value) > Const.JUDGEROLE) {
			return ServerResponse.createByErrorMessage("权限不够");
		}

		course.setCourseId(courseId);
		return courseService.updateCourse(course);
	}

	@PostMapping
	public ServerResponse register(HttpServletRequest  request,
								   @RequestBody Course course) {

		//Cookie验证
		String key = CookieUtil.getUid(request, Const.JSESSIONID);
		String value =redis.get(key);
		if (StringUtils.isBlank(value)) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");

		}

		//判断权限
		if (Integer.parseInt(value) > Const.JUDGEROLE) {
			return ServerResponse.createByErrorMessage("权限不够");
		}

		return  courseService.insertCourse(course);
	}

}
