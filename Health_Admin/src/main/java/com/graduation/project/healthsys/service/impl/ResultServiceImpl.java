/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.project.healthsys.mapper.ResultDao;
import com.graduation.project.healthsys.bean.Result;
import com.graduation.project.healthsys.service.IResultService;
import org.springframework.stereotype.Service;

/**
 * 功能说明:
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Service
public class ResultServiceImpl extends ServiceImpl<ResultDao, Result> implements IResultService {
	
}
