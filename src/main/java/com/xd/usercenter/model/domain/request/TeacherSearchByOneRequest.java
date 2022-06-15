package com.xd.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 教师信息请求体
 *
 * @author XD
 */
@Data
public class TeacherSearchByOneRequest implements Serializable {


    private static final long serialVersionUID = -6535788440880156378L;

    private String teacherName;

    private String sex;

    private String age;

    private String teacherLevel;

    private String collage;

    private String tel;




}
