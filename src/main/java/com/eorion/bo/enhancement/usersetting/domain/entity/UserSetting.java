package com.eorion.bo.enhancement.usersetting.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.eorion.bo.enhancement.usersetting.domain.handler.JsonTypeHandler;
import lombok.Data;

import java.util.Map;

@TableName(value = "ENHANCEMENT_USER_SETTING", autoResultMap = true)
@Data
public class UserSetting {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "USER_ID")
    private String userId;

    @TableField(value = "PREFERENCE_JSON", typeHandler = JsonTypeHandler.class)
    private Map<String, Object> preferenceJson;
}
