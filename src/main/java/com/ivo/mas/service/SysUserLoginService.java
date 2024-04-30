package com.ivo.mas.service;

import com.ivo.mas.mapper.SysUserLoginMapper;
import com.ivo.mas.pojo.SysUserLogin;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysUserLoginService {

    @Resource
    private SysUserLoginMapper sysUserLoginMapper;
    
    /**
     * 查询多条数据
     *
     * @param sysUserLogin 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> querySysUserLoginList(SysUserLogin sysUserLogin) {
        QueryWrapper<SysUserLogin> queryWrapper = new QueryWrapper<SysUserLogin>(sysUserLogin);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",sysUserLoginMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param sysUserLogin 查询条件
     * @return 对象
     */
    public ResponseResult<Object> querySysUserLoginObject(SysUserLogin sysUserLogin) {
        QueryWrapper<SysUserLogin> queryWrapper = new QueryWrapper<SysUserLogin>(sysUserLogin);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",sysUserLoginMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param sysUserLogin 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addSysUserLogin(SysUserLogin sysUserLogin) {
        Integer uid = SessionUtils.getUserId();
        sysUserLogin.setLid(null);
        sysUserLogin.setValidFlag(1);
        sysUserLogin.setCreateTime(new Date());
        sysUserLogin.setUpdateTime(new Date());
        sysUserLogin.setCreater(uid);
        sysUserLogin.setUpdater(uid);
        sysUserLoginMapper.insert(sysUserLogin);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",sysUserLogin);
    }
    
    /**
     * 修改一条数据
     *
     * @param sysUserLogin 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editSysUserLogin(SysUserLogin sysUserLogin) {
        Integer uid = SessionUtils.getUserId();
        sysUserLogin.setUpdateTime(new Date());
        sysUserLogin.setUpdater(uid);
        sysUserLoginMapper.updateById(sysUserLogin);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",sysUserLogin);
    }

}
