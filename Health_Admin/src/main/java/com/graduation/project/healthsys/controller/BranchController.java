/*
 * 软件版权:
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019-04-13    xiemd       新增
 * ========    =======  ============================================
 */
package com.graduation.project.healthsys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.project.healthsys.bean.Branch;
import com.graduation.project.healthsys.enums.Resultenum;
import com.graduation.project.healthsys.exception.HtException;
import com.graduation.project.healthsys.service.IBranchService;
import com.graduation.project.healthsys.util.ParamsUtils;
import com.graduation.project.healthsys.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能说明: 前端控制器
 * 开发人员: xiemd     <br>
 * 开发时间: 2019-04-13 <br>
 * 功能描述: <br>
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class BranchController {

  @Autowired
  private IBranchService branchService;

  @RequestMapping(value ="/branch", method = RequestMethod.GET)
  public Object get() {
    return ResultUtil.success(branchService.list());
  }

  @RequestMapping(value ="/branch", method = RequestMethod.POST)
  public Object add(@RequestBody Branch branch) {
    branch.setBranchNo(IdWorker.getIdStr());
    branchService.save(branch);
    return ResultUtil.success();
  }

  @RequestMapping(value ="/branch", method = RequestMethod.PUT)
  public Object update(Branch branch) {
    if (Objects.isNull(branch.getBranchNo()) || Objects.equals("", branch)) {
      throw new HtException(Resultenum.MISSING_PARAM, "请选择分院");
    }

    Branch dbBranch = branchService.getById(branch.getBranchNo());

    if (Objects.isNull(dbBranch)) {
      throw new HtException(Resultenum.DATA_NOT_EXIST);
    }
    branchService.updateById(branch);

    return ResultUtil.success();
  }

  @PostMapping("/pageList")
  public Object pageList(Map<String, Object> param){

    String currentStr = ParamsUtils.stringParam(param, "current");

    Integer current = Integer.parseInt(currentStr);

    IPage<Branch> page = new Page<>(current, 20);

    page = branchService.page(page);

    return ResultUtil.success(page);
  }
}
