/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/17      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能说明:
 * 开发人员: xmd    xmd@hzregtech.com <br>
 * 开发时间: 2019/4/17 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class DateUtils {

  public static String DATE_FORMAT = "yyyy-MM-dd";

  public static String dateToDateStr(Date date) {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
    datestr = df.format(date);
    return datestr;
  }

  public static Date stringToDate(String datestr, String dateformat) {
    Date date = new Date();
    SimpleDateFormat df = new SimpleDateFormat(dateformat);
    try {
      date = df.parse(datestr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  public static  String getNowDateTimeStr() {
    String datestr = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    datestr = df.format(new Date());
    return datestr;
  }
}