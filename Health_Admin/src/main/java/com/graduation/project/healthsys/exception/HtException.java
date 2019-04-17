/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/13      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.exception;

import com.graduation.project.healthsys.enums.Resultenum;
import lombok.Data;

/**
 * 功能说明:
 * 开发人员: xmd    xmd@hzregtech.com <br>
 * 开发时间: 2019/4/13 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Data
public class HtException extends RuntimeException {

  Integer code;

  public HtException() {
    super("服务器出现异常");
    this.code = 101;
  }

  public HtException (Integer code, String msg) {
    super(msg);
    this.code = code;
  }
  public HtException (String msg) {
    super(msg);
    this.code = 101;
  }

  public HtException (Resultenum resultenum) {
    super(resultenum.getMsg());
    this.code = resultenum.getCode();
  }

  public HtException (Resultenum resultenum, String msg) {
    super(msg);
    this.code = resultenum.getCode();
  }
}