package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * SysUserLogin表实体类
 * 用户登录表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserLogin implements Serializable {
    private static final long serialVersionUID = 693918173154677055L;
    /**
     * 登录id,自增
     */
    @TableId(type = IdType.AUTO)
    private Integer lid;
    /**
     * 登录token
     */
    private String token;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户唯一id
     */
    private String openId;
    /**
     * 有效标识
     */
    private Integer validFlag;
    /**
     * 创建人id
     */
    private Integer creater;
    /**
     * 更新人id
     */
    private Integer updater;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注说明
     */
    private String memo;
    /**
     * 用户加密ID
     */
    private String sessionKey;


}

