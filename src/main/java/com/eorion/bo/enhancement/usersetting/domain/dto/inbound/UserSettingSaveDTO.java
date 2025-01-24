package com.eorion.bo.enhancement.usersetting.domain.dto.inbound;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Map;

@Data
public class UserSettingSaveDTO {
    private Integer id;

    private String userId;

    private Map<String, Object> preferenceJson;

    public String getPreferenceJson() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this.preferenceJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
