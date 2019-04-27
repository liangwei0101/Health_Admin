/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */

package com.graduation.project.healthsys.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能说明:
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subscribe implements Serializable{

private static final long serialVersionUID=1L;

  /**
   * 
   */
  @TableId
  private String id;
  /**
   * 
   */
  private String idCard;
  /**
   * 预约方式
   */
  private String subscribeType;
  /**
   * 预约类型
   */
  private Date subscribeTime;
  /**
   * 分院id
   */
  private String branchNo;
  /**
   * 预约id
   */
  private int serialNo;

  /**
   * 套餐id
   */
  private String mealId;
}
