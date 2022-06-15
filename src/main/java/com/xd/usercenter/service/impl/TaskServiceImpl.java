package com.xd.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.usercenter.model.domain.Task;
import com.xd.usercenter.service.TaskService;
import com.xd.usercenter.mapper.TaskMapper;
import org.springframework.stereotype.Service;

/**
* @author 24557
* @description 针对表【task】的数据库操作Service实现
* @createDate 2022-06-15 15:56:17
*/
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task>
    implements TaskService{

}




