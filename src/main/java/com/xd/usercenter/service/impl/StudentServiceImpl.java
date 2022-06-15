package com.xd.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.usercenter.model.domain.Student;
import com.xd.usercenter.service.StudentService;
import com.xd.usercenter.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 24557
* @description 针对表【student】的数据库操作Service实现
* @createDate 2022-06-15 13:41:22
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




