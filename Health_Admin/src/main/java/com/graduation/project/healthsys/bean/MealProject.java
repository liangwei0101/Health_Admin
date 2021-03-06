/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-17    xiemd       新增
 * ========    =======  ============================================
 */

package com.graduation.project.healthsys.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 功能说明:
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-17 <br>
 * 功能描述: <br>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("meal_project")
public class MealProject implements Serializable{

private static final long serialVersionUID=1L;

  /**
   * 
   */
  @TableId
  private String id;
  /**
   * 套餐id
   */
  private String mealId;
  /**
   * 项目id
   */
  private String projectId;


}
