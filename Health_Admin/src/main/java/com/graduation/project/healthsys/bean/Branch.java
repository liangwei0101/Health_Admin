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
public class Branch implements Serializable{

private static final long serialVersionUID=1L;

  /**
   * 
   */
  @TableId
  private String branchNo;
  /**
   * 
   */
  private String name;
  /**
   * 
   */
  private String address;

  private int subscribeMaxCount;
}
