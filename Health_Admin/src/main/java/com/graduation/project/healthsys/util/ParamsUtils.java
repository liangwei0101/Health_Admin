/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/13      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.util;

import java.util.Map;
import java.util.Objects;

/**
 * 功能说明:
 * 开发人员: xmd
 * 开发时间: 2019/4/13 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class ParamsUtils {

  public static String stringParam(Map<String, Object> param, String key) {
    Object value = param.get(key);
    return Objects.isNull(value) ? null : value.toString();
  }

}