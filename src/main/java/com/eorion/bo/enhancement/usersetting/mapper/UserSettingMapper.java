package com.eorion.bo.enhancement.usersetting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eorion.bo.enhancement.usersetting.domain.dto.inbound.UserSettingSaveDTO;
import com.eorion.bo.enhancement.usersetting.domain.entity.UserSetting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSettingMapper extends BaseMapper<UserSetting> {

    void updateByUserId(UserSettingSaveDTO saveDto);

    void saveUserSetting(UserSettingSaveDTO saveDto);
}