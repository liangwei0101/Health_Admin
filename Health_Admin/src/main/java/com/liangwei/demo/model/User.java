package com.liangwei.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体的类
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class User {

    private int no;

    private String name;

    private String email;

}
