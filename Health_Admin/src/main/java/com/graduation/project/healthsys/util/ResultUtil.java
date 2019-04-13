/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/13      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明:
 * 开发人员: xmd    xmd@hzregtech.com <br>
 * 开发时间: 2019/4/13 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class ResultUtil {

  public static Map<String, Object> error(){
    Map<String, Object> result = new HashMap<>();
    result.put("code", "101");
    result.put("msg", "");
    return result;
  }

  public static Map<String, Object> result(Integer code, String msg) {
    Map<String, Object> result = new HashMap<>();
    result.put("code", code);
    result.put("msg", msg);
    return result;
  }

  public static Map<String, Object> success() {
    Map<String, Object> result = new HashMap<>();
    result.put("code", 200);
    result.put("msg", "操作成功");
    return result;
  }

  public static Map<String, Object> success(Object obj) {
    Map<String, Object> result = new HashMap<>();
    result.put("code", 200);
    result.put("msg", "操作成功");
    result.put("data", obj);
    return result;
  }
}