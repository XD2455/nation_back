package com.xd.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.usercenter.common.BaseResponse;
import com.xd.usercenter.common.ErrorCode;
import com.xd.usercenter.common.ResultUtils;
import com.xd.usercenter.exception.BusinessException;
import com.xd.usercenter.model.domain.Task;
import com.xd.usercenter.model.domain.User;
import com.xd.usercenter.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.xd.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.xd.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 作业接口
 *
 * @author XD
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;


    /**
     * 查询作业信息 仅管理员
     * @param taskName  taskName 可无
     * @param request   http
     * @return  list
     */
    
    @GetMapping("/search")
    public BaseResponse<List<Task>> searchTask(String taskName, HttpServletRequest request) {
        if (!isAdmin(request)) {
           throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(taskName)) {
            queryWrapper.like("taskName", taskName);
        }
        List<Task> taskList = taskService.list(queryWrapper);
        
        return ResultUtils.success(taskList);
        
    }

    /**
     * 单点查询作业信息
     * @param searchTask   Task类
     * @param request   http
     * @return  list
     * 
     */
    @PostMapping("/searchTasksByOne")
    public BaseResponse<List<Task>> searchTasksByOne(@RequestBody Task  searchTask, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        String stuId = searchTask.getStuId();
        String taskName = searchTask.getTaskName();
        String major = searchTask.getMajor();
        String stuClass = searchTask.getStuClass();
        String course = searchTask.getCourse();
        String score = searchTask.getScore();
        String extra = searchTask.getExtra();
        String subDate = searchTask.getSubDate();
        String taskData = searchTask.getTaskData();
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(taskName)) {
            queryWrapper.like("taskName", taskName);
        }
        if (StringUtils.isNotBlank(stuId)) {
            queryWrapper.like("stuId", stuId);
        }
        if (StringUtils.isNotBlank(major)) {
            queryWrapper.like("major", major);
        }
        if (StringUtils.isNotBlank(stuClass)) {
            queryWrapper.like("stuClass", stuClass);
        }
        if (StringUtils.isNotBlank(course)) {
            queryWrapper.like("course", course);
        }
        if (StringUtils.isNotBlank(score)) {
            queryWrapper.like("score", score);
        }
        if (StringUtils.isNotBlank(extra)) {
            queryWrapper.like("extra", extra);
        }
        if (StringUtils.isNotBlank(subDate)) {
            queryWrapper.like("subDate", subDate);
        }
        if (StringUtils.isNotBlank(taskData)) {
            queryWrapper.like("taskData", taskData);
        }

        List<Task> taskList = taskService.list(queryWrapper);
        return ResultUtils.success(taskList);
    }

    /**
     * 删除作业
     * @param deleteTask   Task类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteTask(@RequestBody Task deleteTask, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (deleteTask.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"id不能小于0");
        }
        boolean b = taskService.removeById(deleteTask.getId());
        return ResultUtils.success(b);
    }

    /**
     * 添加、修改 作业
     * @param changeTask    Task 类
     * @param request   http
     * @return  boolean
     */
    @PostMapping("/save")
    public BaseResponse<Boolean> changeTask (@RequestBody Task  changeTask, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        boolean b = taskService.saveOrUpdate(changeTask);
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
