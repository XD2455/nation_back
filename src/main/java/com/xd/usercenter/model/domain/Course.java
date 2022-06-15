package com.xd.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Date deleteTime;

    /**
     * 
     */
    private String cid;

    /**
     * 
     */
    private String collage;

    /**
     * 
     */
    private String course;

    /**
     * 
     */
    private String cData;

    /**
     * 
     */
    private String extra;

    /**
     * 
     */
    private Integer counts;

    /**
     * 
     */
    private String stuId;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * 
     */
    private String teaId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}