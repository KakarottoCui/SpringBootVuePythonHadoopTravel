package com.ivo.mas.service;

import com.ivo.mas.mapper.SysUserMapper;
import com.ivo.mas.pojo.SysUser;

import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.CommonFunction;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivo.mas.system.utils.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    
    /**
     * 查询多条数据
     *
     * @param sysUser 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> querySysUserList(SysUser sysUser) {
        String nickName = "";
        nickName = sysUser.getNickName();
        if(nickName!=null){
            sysUser.setNickName(null);
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>(sysUser);
        if(nickName!=null)
            queryWrapper.like("nick_name",nickName);
        queryWrapper.orderByDesc("uid");
        List<SysUser> sysUserList = sysUserMapper.selectList(queryWrapper);

        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",sysUserList);
    }
    
    /**
     * 查询一条数据
     *
     * @param sysUser 查询条件
     * @return 对象
     */
    public ResponseResult<Object> querySysUserObject(SysUser sysUser) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>(sysUser);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",sysUserMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param sysUser 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addSysUser(SysUser sysUser) {
        Integer uid = SessionUtils.getUserId();
        sysUser.setUid(null);
        sysUser.setValidFlag(1);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setCreater(uid);
        sysUser.setUpdater(uid);
        setPic(sysUser);
        sysUserMapper.insert(sysUser);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",sysUser);
    }
    
    /**
     * 修改一条数据
     *
     * @param sysUser 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editSysUser(SysUser sysUser) {
        SysUser param = new SysUser();
        QueryWrapper<SysUser> queryWrapper2 = new QueryWrapper<SysUser>();
        queryWrapper2.eq("valid_flag",1);
        queryWrapper2.and(wq->{
            wq.eq("nick_name",sysUser.getNickName());
        });
        param = sysUserMapper.selectOne(queryWrapper2);
        if(param!=null && !param.getUid().equals(sysUser.getUid())){
            return new ResponseResult(ResponseCode.SERVICE_ERROR, "用户重名");
        }

        Integer uid = SessionUtils.getUserId();
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdater(uid);
        if(sysUser.getPassword()==null){
            sysUser.setPassword("MTIzNDU2");
        }
        setPic(sysUser);
        sysUserMapper.updateById(sysUser);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",sysUser);
    }

    public void setPic(SysUser nmMusic){
        if(StringUtils.isNotEmpty(nmMusic.getPic()) && nmMusic.getPic().length()>100 ){
            String fileName = CommonFunction.SaveBase64Pic(nmMusic.getPic());
            nmMusic.setPic(fileName);
        }
    }

    public ResponseResult<Object> getAllJob() {
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"成功",sysUserMapper.getAllJob());
    }

}
