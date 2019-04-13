/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/4/13      xmd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.util;

import java.security.MessageDigest;

/**
 * 功能说明:
 * 开发人员: xmd
 * 开发时间: 2019/4/13 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class Md5Util {
  public static String create32Md5(String word) {
    StringBuilder hexString = null;
    byte[] defaultBytes = word.getBytes();
    try {
      MessageDigest algorithm = MessageDigest.getInstance("MD5");
      algorithm.reset();
      algorithm.update(defaultBytes);
      byte messageDigest[] = algorithm.digest();
      hexString = new StringBuilder();
      for (int i = 0; i < messageDigest.length; i++) {
        if (Integer.toHexString(0xFF & messageDigest[i]).length() == 1) {
          hexString.append(0);
        }
        hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
      }
      messageDigest.toString();
    } catch (Exception e) {
    }
    return hexString.toString();
  }

}