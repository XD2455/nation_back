package com.xd.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.usercenter.common.BaseResponse;
import com.xd.usercenter.common.ErrorCode;
import com.xd.usercenter.common.ResultUtils;
import com.xd.usercenter.exception.BusinessException;
import com.xd.usercenter.model.domain.Student;
import com.xd.usercenter.model.domain.Teacher;
import com.xd.usercenter.model.domain.User;
import com.xd.usercenter.service.TeacherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.xd.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.xd.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 教师接口
 *
 * @author XD
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;


    /**
     * 查询教师信息 仅管理员
     * @param teacherName  teacherName 可无
     * @param request   http
     * @return  list
     */
    
    @GetMapping("/search")
    public BaseResponse<List<Teacher>> searchTeacher(String teacherName, HttpServletRequest request) {
        if (!isAdmin(request)) {
           throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(teacherName)) {
            queryWrapper.like("teacherName", teacherName);
        }
        List<Teacher> teacherList = teacherService.list(queryWrapper);
        
        return ResultUtils.success(teacherList);
        
    }

    /**
     * 单点查询教师信息
     * @param searchTeacher    User类
     * @param request   http
     * @return  list
     * 
     */
    @PostMapping("/searchTeachersByOne")
    public BaseResponse<List<Teacher >> searchTeachersByOne(@RequestBody Teacher  searchTeacher, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        String teacherName = searchTeacher.getTeacherName();
        String sex = searchTeacher.getSex();
        Integer age = searchTeacher.getAge();
        String teacherLevel = searchTeacher.getTeacherLevel();
        String collage = searchTeacher.getCollage();
        String tel = searchTeacher.getTel();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(teacherName)) {
            queryWrapper.like("teacherName", teacherName);
        }
        if (StringUtils.isNotBlank(sex)) {
            queryWrapper.like("sex", sex);
        }
        if (age!=null) {
            queryWrapper.eq("age", age);
        }
        if (StringUtils.isNotBlank(teacherLevel)) {
            queryWrapper.like("teacherLevel", teacherLevel);
        }
        if (StringUtils.isNotBlank(collage)) {
            queryWrapper.like("collage", collage);
        }

        if (StringUtils.isNotBlank(tel)) {
            queryWrapper.like("tel", tel);
        }
        List<Teacher> teacherList = teacherService.list(queryWrapper);
        return ResultUtils.success(teacherList);
    }

    /**
     * 删除教师
     * @param deleteTeacher   Teacher类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTeacher(@RequestBody Teacher deleteTeacher, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (deleteTeacher.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id不能小于0");
        }
        boolean b = teacherService.removeById(deleteTeacher.getId());
        return ResultUtils.success(b);
    }

    /**
     * 添加、修改 用户
     * @param changeTeacher    Teacher 类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/save")
    public BaseResponse<Boolean> changeTeacher (@RequestBody Teacher  changeTeacher, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        boolean b = teacherService.saveOrUpdate(changeTeacher);
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
