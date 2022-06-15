package com.xd.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.usercenter.model.domain.Course;
import com.xd.usercenter.service.CourseService;
import com.xd.usercenter.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author 24557
* @description 针对表【course】的数据库操作Service实现
* @createDate 2022-06-15 15:18:25
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




