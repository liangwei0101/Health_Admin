package com.graduation.project.healthsys.enums;/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/13      xmd       新增
 * ========    =======  ============================================
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Resultenum {

  ERROR(101, "服务错误"),
  MISSING_PARAM(102, "缺少必要参数"),
  DATA_NOT_EXIST(103, "数据不存在")
  ;
  Integer code;
  String msg;
}