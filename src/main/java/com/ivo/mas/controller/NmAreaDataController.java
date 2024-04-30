package com.ivo.mas.controller;
import com.ivo.mas.service.NmAreaDataService;
import com.ivo.mas.pojo.NmAreaData;
import com.ivo.mas.system.ResponseFormat.BaseResponse;
import com.ivo.mas.system.ResponseFormat.ResponseResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@BaseResponse
@RequestMapping("/nmAreaData")
public class NmAreaDataController {

    @Resource
    NmAreaDataService nmAreaDataService;
    
    /**
     * 查询多条数据
     *
     * @param nmAreaData 查询条件
     * @return 对象列表
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public ResponseResult<Object> queryNmAreaDataList(@RequestBody NmAreaData nmAreaData){
        return nmAreaDataService.queryNmAreaDataList(nmAreaData);
    }
    /**
     * 查询一条数据
     *
     * @param nmAreaData 查询条件
     * @return 对象
     */
    @RequestMapping("/queryObject")
    @ResponseBody
    public ResponseResult<Object> queryNmAreaDataObject(@RequestBody NmAreaData nmAreaData){
        return nmAreaDataService.queryNmAreaDataObject(nmAreaData);
    }
    /**
     * 新增一条数据
     *
     * @param nmAreaData 新增数据实体类
     * @return 新增对象
     */
    @RequestMapping("/addNmAreaData")
    @ResponseBody
    public ResponseResult<Object> addNmAreaData(@RequestBody NmAreaData nmAreaData){
        return nmAreaDataService.addNmAreaData(nmAreaData);
    }
    /**
     * 修改一条数据
     *
     * @param nmAreaData 修改数据实体类
     * @return 修改后对象
     */
    @RequestMapping("/editNmAreaData")
    @ResponseBody
    public ResponseResult<Object> editNmAreaData(@RequestBody NmAreaData nmAreaData){
        return nmAreaDataService.editNmAreaData(nmAreaData);
    }

    /**
     * 导入数据
     * @return 修改后对象
     */
    @RequestMapping("/importData")
    @ResponseBody
    public ResponseResult<Object> importData(@RequestParam("file") MultipartFile file){
        return nmAreaDataService.importData(file);
    }

    @RequestMapping("/getHotList")
    @ResponseBody
    public ResponseResult<Object> getHotList(){
        return nmAreaDataService.getHotList();
    }

        @RequestMapping("/getPushList")
    @ResponseBody
    public ResponseResult<Object> getPushList(){
        return nmAreaDataService.getPushList();
    }
}
