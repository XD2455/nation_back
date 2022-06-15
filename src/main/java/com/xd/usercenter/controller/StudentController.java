package com.xd.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.usercenter.common.BaseResponse;
import com.xd.usercenter.common.ErrorCode;
import com.xd.usercenter.common.ResultUtils;
import com.xd.usercenter.exception.BusinessException;
import com.xd.usercenter.model.domain.Student;
import com.xd.usercenter.model.domain.User;
import com.xd.usercenter.model.domain.request.StudentSearchByOneRequest;
import com.xd.usercenter.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.xd.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.xd.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 学生接口
 *
 * @author XD
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;


    /**
     * 查询学生信息 仅管理员
     * @param studentName  studentName 可无
     * @param request   http
     * @return  list
     */
    
    @GetMapping("/search")
    public BaseResponse<List<Student>> searchStudent(String studentName, HttpServletRequest request) {
        if (!isAdmin(request)) {
           throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(studentName)) {
            queryWrapper.like("studentName", studentName);
        }
        List<Student> studentList = studentService.list(queryWrapper);
        
        return ResultUtils.success(studentList);
        
    }

    /**
     * 单点查询学生信息
     * @param studentSearchByOneRequest   Student类
     * @param request   http
     * @return  list
     * 
     */
    @PostMapping("/searchStuByOne")
    public BaseResponse<List<Student>> searchStudentsByOne(@RequestBody StudentSearchByOneRequest studentSearchByOneRequest, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        String stuName = studentSearchByOneRequest.getStuName();
        String sex = studentSearchByOneRequest.getSex();
        Integer age = studentSearchByOneRequest.getAge();
        Integer studyDate = studentSearchByOneRequest.getStudyDate();
        String major = studentSearchByOneRequest.getMajor();
        String stuClass = studentSearchByOneRequest.getStuClass();
        String tel = studentSearchByOneRequest.getTel();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(stuName)) {
            queryWrapper.like("stuName", stuName);
        }
        if (StringUtils.isNotBlank(sex)) {
            queryWrapper.like("sex", sex);
        }
        if (age!=null) {
            queryWrapper.eq("age", age);
        }
        if (studyDate!=null) {
            queryWrapper.eq("studyDate", studyDate);
        }
        if (StringUtils.isNotBlank(major)) {
            queryWrapper.like("major", major);
        }
        if (StringUtils.isNotBlank(stuClass)) {
            queryWrapper.like("stuClass", stuClass);
        }

        if (StringUtils.isNotBlank(tel)) {
            queryWrapper.like("tel", tel);
        }
        List<Student> studentList = studentService.list(queryWrapper);
        return ResultUtils.success(studentList);
    }

    /**
     * 删除学生
     * @param deleteStudent   Student类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteStudent(@RequestBody Student deleteStudent, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (deleteStudent.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id不能小于0");
        }
        boolean b = studentService.removeById(deleteStudent.getId());
        return ResultUtils.success(b);
    }

    /**
     * 添加、修改 学生
     * @param changeStudent    Student类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/save")
    public BaseResponse<Boolean> changeStudent(@RequestBody Student changeStudent, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        boolean b = studentService.saveOrUpdate(changeStudent);
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
