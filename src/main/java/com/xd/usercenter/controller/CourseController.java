package com.xd.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.usercenter.common.BaseResponse;
import com.xd.usercenter.common.ErrorCode;
import com.xd.usercenter.common.ResultUtils;
import com.xd.usercenter.exception.BusinessException;
import com.xd.usercenter.model.domain.Course;
import com.xd.usercenter.model.domain.User;
import com.xd.usercenter.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.xd.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.xd.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 课程接口
 *
 * @author XD
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;


    /**
     * 查询课程信息 仅管理员
     * @param courseName  courseName 可无
     * @param request   http
     * @return  list
     */
    
    @GetMapping("/search")
    public BaseResponse<List<Course>> searchCourse(String courseName, HttpServletRequest request) {
        if (!isAdmin(request)) {
           throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(courseName)) {
            queryWrapper.like("courseName", courseName);
        }
        List<Course> courseList = courseService.list(queryWrapper);
        
        return ResultUtils.success(courseList);
        
    }

    /**
     * 单点查询课程信息
     * @param searchCourse    Course类
     * @param request   http
     * @return  list
     * 
     */
    @PostMapping("/searchCoursesByOne")
    public BaseResponse<List<Course >> searchCoursesByOne(@RequestBody Course  searchCourse, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        String cId = searchCourse.getCid();
        String stuId = searchCourse.getStuId();
        String collage = searchCourse.getCollage();
        String course = searchCourse.getCourse();
        String cData = searchCourse.getCData();
        String extra = searchCourse.getExtra();
        Integer counts = searchCourse.getCounts();
        String teaId = searchCourse.getTeaId();

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(cId)) {
            queryWrapper.like("cId", cId);
        }
        if (StringUtils.isNotBlank(stuId)) {
            queryWrapper.like("stuId", stuId);
        }
        if (StringUtils.isNotBlank(cData)) {
            queryWrapper.like("cData", cData);
        }
        if (counts!=null) {
            queryWrapper.eq("counts", counts);
        }
        if (StringUtils.isNotBlank(course)) {
            queryWrapper.like("course", course);
        }
        if (StringUtils.isNotBlank(collage)) {
            queryWrapper.like("collage", collage);
        }

        if (StringUtils.isNotBlank(extra)) {
            queryWrapper.like("extra", extra);
        }
        if (StringUtils.isNotBlank(teaId)) {
            queryWrapper.like("teaId", teaId);
        }
        List<Course> courseList = courseService.list(queryWrapper);
        return ResultUtils.success(courseList);
    }

    /**
     * 删除课程
     * @param deleteCourse   Course类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteCourse(@RequestBody Course deleteCourse, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (deleteCourse.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id不能小于0");
        }
        boolean b = courseService.removeById(deleteCourse.getId());
        return ResultUtils.success(b);
    }

    /**
     * 添加、修改 课程
     * @param changeCourse    Course 类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/save")
    public BaseResponse<Boolean> changeCourse (@RequestBody Course  changeCourse, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        boolean b = courseService.saveOrUpdate(changeCourse);
        return ResultUtils.success(b);
    }

    /**
     * 是否为管理员
     * @param request   http
     * @return  boolean
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }



}
