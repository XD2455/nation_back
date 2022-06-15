package com.xd.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 学生信息请求体
 *
 * @author XD
 */
@Data
public class StudentSearchByOneRequest implements Serializable {


    private static final long serialVersionUID = 1280426480570011355L;

    private String stuName;

    private String sex;

    private Integer age;

    private Integer studyDate;

    private String major;

    private String stuClass;

    private String tel;


}
