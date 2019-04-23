/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-17    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.project.healthsys.bean.MealProject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 功能说明:
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-17 <br>
 * 功能描述: <br>
 */
public interface MealProjectDao extends BaseMapper<MealProject> {

    /**
     *  @Param 是参数的别名，在sql中可以动态获取的。
     *
     * @param mealId
     * @return
     */

    @Select("DELETE FROM meal_project WHERE meal_id = #{mealId}")
    Boolean DeleteByMealId (@Param("mealId")String mealId);
}