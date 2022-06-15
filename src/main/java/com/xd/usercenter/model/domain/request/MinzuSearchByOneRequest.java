package com.xd.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 民族资源请求体
 *
 * @author XD
 */
@Data
public class MinzuSearchByOneRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String minzuName;

    private String minzuType;

    private String minzuSource;
}
