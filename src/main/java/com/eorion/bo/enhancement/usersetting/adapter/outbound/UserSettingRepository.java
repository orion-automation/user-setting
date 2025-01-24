package com.eorion.bo.enhancement.usersetting.adapter.outbound;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eorion.bo.enhancement.usersetting.domain.dto.inbound.UserSettingSaveDTO;
import com.eorion.bo.enhancement.usersetting.domain.entity.UserSetting;
import com.eorion.bo.enhancement.usersetting.mapper.UserSettingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSettingRepository extends ServiceImpl<UserSettingMapper, UserSetting> {

    private final UserSettingMapper mapper;

    public void updateByUserId(UserSettingSaveDTO saveDto) {
        mapper.updateByUserId(saveDto);
    }

    public void saveUserSetting(UserSettingSaveDTO saveDto) {
        mapper.saveUserSetting(saveDto);
    }
}
