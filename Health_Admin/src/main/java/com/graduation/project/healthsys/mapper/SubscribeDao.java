/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.project.healthsys.bean.Subscribe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能说明:
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
public interface SubscribeDao extends BaseMapper<Subscribe> {



    /**
     *  @Param 是参数的别名，在sql中可以动态获取的。
     *
     * @param idCard
     * @return
     */

    @Select("select * subscribe WHERE id_card = #{idCard}")
    List<Subscribe> SelectByIdCard (@Param("idCard")String idCard);
}