package com.ivo.mas.controller;
import com.ivo.mas.service.SysUserLoginService;
import com.ivo.mas.pojo.SysUserLogin;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/sysUserLogin")
public class SysUserLoginController {

    @Resource
    SysUserLoginService sysUserLoginService;
    
    /**
     * 查询多条数据
     *
     * @param sysUserLogin 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> querySysUserLoginList(@RequestBody SysUserLogin sysUserLogin){
        return sysUserLoginService.querySysUserLoginList(sysUserLogin);
    }
    /**
     * 查询一条数据
     *
     * @param sysUserLogin 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> querySysUserLoginObject(@RequestBody SysUserLogin sysUserLogin){
        return sysUserLoginService.querySysUserLoginObject(sysUserLogin);
    }
    /**
     * 新增一条数据
     *
     * @param sysUserLogin 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addSysUserLogin")
    @ResponseBody
    public ResponseResult<Object> addSysUserLogin(@RequestBody SysUserLogin sysUserLogin){
        return sysUserLoginService.addSysUserLogin(sysUserLogin);
    }
    /**
     * 修改一条数据
     *
     * @param sysUserLogin 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editSysUserLogin")
    @ResponseBody
    public ResponseResult<Object> editSysUserLogin(@RequestBody SysUserLogin sysUserLogin){
        return sysUserLoginService.editSysUserLogin(sysUserLogin);
    }
    
}
