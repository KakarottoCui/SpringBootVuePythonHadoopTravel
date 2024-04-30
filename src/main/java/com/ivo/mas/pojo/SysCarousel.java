package com.ivo.mas.pojo;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * SysCarousel表实体类
 * 通用轮播图
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysCarousel implements Serializable {
    private static final long serialVersionUID = -23468446840231287L;

/**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

/**
     * 标题
     */    
    private String title;
/**
     * 图片名/路径
     */    
    private String pic;
/**
     * 跳转url
     */    
    private String url;
/**
     * 有效标识
     */    
    private Integer validFlag;
/**
     * 创建人ID
     */    
    private Integer creater;
/**
     * 更新人ID
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
     * 备注
     */    
    private String memo;
/**
     * 说明文字
     */    
    private String content;


}
