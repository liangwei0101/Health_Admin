/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/13      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.handler;

import com.graduation.project.healthsys.exception.HtException;
import com.graduation.project.healthsys.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 功能说明:
 * 开发人员: xmd
 * 开发时间: 2019/4/13 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@ControllerAdvice
public class RhExceptionHandler {

  @ExceptionHandler({ HtException.class })
  @ResponseBody
  public Object rhExceptionHandler(HtException e) {
    return ResultUtil.result(e.getCode(), e.getMessage());
  }
}