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
 * @TableName task
 */
@TableName(value ="task")
@Data
public class Task implements Serializable {
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
    private Date deleted_at;

    /**
     * 
     */
    private String stuId;

    /**
     * 
     */
    private String taskName;

    /**
     * 
     */
    private String major;

    /**
     * 
     */
    private String stuClass;

    /**
     * 
     */
    private String course;

    /**
     * 
     */
    private String score;

    /**
     * 
     */
    private String extra;

    /**
     * 
     */
    private String subDate;

    /**
     * 
     */
    private String taskData;

    /**
     * 
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}