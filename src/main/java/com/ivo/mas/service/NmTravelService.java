package com.ivo.mas.service;

import com.alibaba.fastjson.JSONObject;
import com.ivo.mas.mapper.NmTravelMapper;
import com.ivo.mas.pojo.NmTravel;

import com.ivo.mas.pojo.SysUser;
import com.ivo.mas.system.ResponseFormat.ResponseResult;
import com.ivo.mas.system.ResponseFormat.ResponseCode;
import com.ivo.mas.system.utils.SessionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivo.mas.system.utils.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NmTravelService {

    @Resource
    private NmTravelMapper nmTravelMapper;
    
    /**
     * 查询多条数据
     *
     * @param nmTravel 查询条件
     * @return 对象列表
     */
    public ResponseResult<Object> queryNmTravelList(NmTravel nmTravel) {
        String text = "";
        text = nmTravel.getProvince();
        if(text!=null){
            nmTravel.setProvince(null);
            nmTravel.setCity(null);
            nmTravel.setArea(null);
        }
        QueryWrapper<NmTravel> queryWrapper = new QueryWrapper<NmTravel>(nmTravel);
        if(text!=null)
            queryWrapper.like("province",text).or().like("area",text).or().like("city",text);
        queryWrapper.orderByDesc("create_time");
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmTravelMapper.selectList(queryWrapper));
    }
    
    /**
     * 查询一条数据
     *
     * @param nmTravel 查询条件
     * @return 对象
     */
    public ResponseResult<Object> queryNmTravelObject(NmTravel nmTravel) {
        QueryWrapper<NmTravel> queryWrapper = new QueryWrapper<NmTravel>(nmTravel);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",nmTravelMapper.selectOne(queryWrapper));
    }
    
    /**
     * 新增一条数据
     *
     * @param nmTravel 新增数据实体类
     * @return 新增对象
     */
    public ResponseResult<Object> addNmTravel(NmTravel nmTravel) {
        int uid = SessionUtils.getUserId();
        nmTravel.setId(null);
        nmTravel.setValidFlag(1);
        nmTravel.setCreateTime(new Date());
        nmTravel.setUpdateTime(new Date());
        nmTravel.setCreater(uid);
        nmTravel.setUpdater(uid);
        nmTravel.setUid(uid);
        nmTravelMapper.insert(nmTravel);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"新增成功",nmTravel);
    }
    
    /**
     * 修改一条数据
     *
     * @param nmTravel 修改数据实体类
     * @return 修改后对象
     */
    public ResponseResult<Object> editNmTravel(NmTravel nmTravel) {
        int uid = SessionUtils.getUserId();
        nmTravel.setUpdateTime(new Date());
        nmTravel.setUpdater(uid);
        nmTravel.setUid(uid);
        nmTravelMapper.updateById(nmTravel);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"修改成功",nmTravel);
    }

    public ResponseResult<Object> getTravelReport(NmTravel nmTravel) {
        List<NmTravel> jobData = nmTravelMapper.getTravelByJobData(StringUtils.isEmpty(nmTravel.getJob())?"律师":nmTravel.getJob());
        List<NmTravel> eduData = nmTravelMapper.getTravelByEduData(StringUtils.isEmpty(nmTravel.getEdu())?"本科":nmTravel.getEdu());
        List<NmTravel> provinceData = nmTravelMapper.getTravelByProvince(StringUtils.isEmpty(nmTravel.getProvince())?"湖北":nmTravel.getProvince());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jobData",jobData);
        jsonObject.put("eduData",eduData);
        jsonObject.put("provinceData",provinceData);
        return new ResponseResult<Object>(ResponseCode.SUCCESS,"查询成功",jsonObject);
    }

}
