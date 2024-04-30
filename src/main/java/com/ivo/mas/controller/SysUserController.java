package com.ivo.mas.controller;
import com.ivo.mas.service.SysUserService;
import com.ivo.mas.pojo.SysUser;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    SysUserService sysUserService;
    
    /**
     * 查询多条数据
     *
     * @param sysUser 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> querySysUserList(@RequestBody SysUser sysUser){
        return sysUserService.querySysUserList(sysUser);
    }
    /**
     * 查询一条数据
     *
     * @param sysUser 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> querySysUserObject(@RequestBody SysUser sysUser){
        return sysUserService.querySysUserObject(sysUser);
    }
    /**
     * 新增一条数据
     *
     * @param sysUser 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addSysUser")
    @ResponseBody
    public ResponseResult<Object> addSysUser(@RequestBody SysUser sysUser){
        return sysUserService.addSysUser(sysUser);
    }
    /**
     * 修改一条数据
     *
     * @param sysUser 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editSysUser")
    @ResponseBody
    public ResponseResult<Object> editSysUser(@RequestBody SysUser sysUser){
        return sysUserService.editSysUser(sysUser);
    }

    @RequestMapping("/getAllJob")
    @ResponseBody
    public ResponseResult<Object> getAllJob(){
        return sysUserService.getAllJob();
    }
}
