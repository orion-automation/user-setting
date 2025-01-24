package com.eorion.bo.enhancement.usersetting.domain.dto.outbound;

import com.eorion.bo.enhancement.usersetting.domain.entity.UserSetting;
import lombok.Data;

import java.util.Map;

@Data
public class UserSettingDTO {

    private String userId;
    private Map<String, Object> preference;

    public static UserSettingDTO fromEntity(UserSetting entity) {
        var result = new UserSettingDTO();
        result.userId = entity.getUserId();
        result.preference = entity.getPreferenceJson();
        return result;
    }
}
