package com.louis.mango.admin.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Builder
@Setter
@Getter
public class User {

    private Long id;

    private String userName;

    private String password;

    private Date updateTime;

    private Date createTime;


}