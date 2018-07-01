package com.hjy.controller.backend;

import com.hjy.common.ServerResponse;
import com.hjy.entity.Course;
import com.hjy.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hjy
 * @create 2018/06/24
 **/
@RestController
@RequestMapping("/management/course")
public class CourseManageController {

	private Logger logger = LoggerFactory.getLogger(CourseManageController.class);

	@Autowired
	private CourseService courseService;

	@PostMapping(value = "list")
	public ServerResponse getList(
								  @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
								  @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
								  @RequestParam(value = "majorName",defaultValue = "") String majorName){
		return courseService.getCourseList(pageNum,pageSize,majorName);
	}
	@DeleteMapping("/{courseIds}")
	public ServerResponse deleteCourseByCourseId(@PathVariable("courseIds")String courseIds) {
		return  courseService.deleteCourseById(courseIds);
	}


	@PostMapping("update")
	public ServerResponse updateCourse(String courseId , String courseName) {

		Course course = new Course();

		logger.info("s" + courseName);

		if (StringUtils.isBlank(courseName)) {
			return ServerResponse.createByErrorMessage("输入参数有误！");
		}

		course.setCourseId(courseId);
		course.setCourseName(courseName);

		return courseService.updateCourse(course);
	}

	@PostMapping
	public ServerResponse register(Course course) {
		return  courseService.insertCourse(course);
	}

}
