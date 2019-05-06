package com.graduation.project.healthsys.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("picture")
public class Picture implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String userId;

    private String url;
}
