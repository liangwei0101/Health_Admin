package com.graduation.project.healthsys.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {

    /**
     *
     */
    @TableId
    private String id;
    /**
     *
     */
    private String name;
}
