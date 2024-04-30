package com.ivo.mas.controller;
import com.ivo.mas.service.SysCarouselService;
import com.ivo.mas.pojo.SysCarousel;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/sysCarousel")
public class SysCarouselController {

    @Resource
    SysCarouselService sysCarouselService;
    
    /**
     * 查询多条数据
     *
     * @param sysCarousel 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> querySysCarouselList(@RequestBody SysCarousel sysCarousel){
        return sysCarouselService.querySysCarouselList(sysCarousel);
    }
    /**
     * 查询一条数据
     *
     * @param sysCarousel 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> querySysCarouselObject(@RequestBody SysCarousel sysCarousel){
        return sysCarouselService.querySysCarouselObject(sysCarousel);
    }
    /**
     * 新增一条数据
     *
     * @param sysCarousel 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addSysCarousel")
    @ResponseBody
    public ResponseResult<Object> addSysCarousel(@RequestBody SysCarousel sysCarousel){
        return sysCarouselService.addSysCarousel(sysCarousel);
    }
    /**
     * 修改一条数据
     *
     * @param sysCarousel 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editSysCarousel")
    @ResponseBody
    public ResponseResult<Object> editSysCarousel(@RequestBody SysCarousel sysCarousel){
        return sysCarouselService.editSysCarousel(sysCarousel);
    }
    
}
