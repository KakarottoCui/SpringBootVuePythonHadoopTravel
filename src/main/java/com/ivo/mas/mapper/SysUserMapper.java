package com.ivo.mas.mapper;

import com.ivo.mas.pojo.NmTravel;
import com.ivo.mas.pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 */

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select(" SELECT job FROM sys_user WHERE valid_flag = 1 GROUP BY job ")
    List<SysUser> getAllJob();

}
