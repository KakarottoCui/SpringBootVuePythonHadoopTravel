package com.ivo.mas.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivo.mas.pojo.SysUser;
import com.ivo.mas.service.NmTravelService;
import com.ivo.mas.pojo.NmTravel;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
@BaseResponse
@RequestMapping("/nmTravel")
public class NmTravelController {

    @Resource
    NmTravelService nmTravelService;
    
    /**
     * 查询多条数据
     *
     * @param nmTravel 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmTravelList(@RequestBody NmTravel nmTravel){
        return nmTravelService.queryNmTravelList(nmTravel);
    }
    /**
     * 查询一条数据
     *
     * @param nmTravel 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmTravelObject(@RequestBody NmTravel nmTravel){
        return nmTravelService.queryNmTravelObject(nmTravel);
    }
    /**
     * 新增一条数据
     *
     * @param nmTravel 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmTravel")
    @ResponseBody
    public ResponseResult<Object> addNmTravel(@RequestBody NmTravel nmTravel){
        return nmTravelService.addNmTravel(nmTravel);
    }
    /**
     * 修改一条数据
     *
     * @param nmTravel 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmTravel")
    @ResponseBody
    public ResponseResult<Object> editNmTravel(@RequestBody NmTravel nmTravel){
        return nmTravelService.editNmTravel(nmTravel);
    }

    /**
     * 获取推荐榜单
     * @return 修改后对象
     */
    @RequestMapping("/getTravelReport")
    @ResponseBody
    public ResponseResult<Object> getTravelReport(@RequestBody NmTravel nmTravel){
        return nmTravelService.getTravelReport(nmTravel);
    }
    
}
