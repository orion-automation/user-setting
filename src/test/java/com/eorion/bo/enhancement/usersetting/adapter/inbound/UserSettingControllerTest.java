package com.eorion.bo.enhancement.usersetting.adapter.inbound;

import com.eorion.bo.enhancement.usersetting.domain.dto.inbound.UserSettingSaveDTO;
import com.eorion.bo.enhancement.usersetting.service.UserSettingService;
import com.eorion.bo.enhancement.usersetting.utils.BatchSQLExecutor;
import org.camunda.bpm.engine.IdentityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserSettingControllerTest {

    private static final HttpHeaders headers = new HttpHeaders();

    static {
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("demo:demo".getBytes(StandardCharsets.UTF_8)));

    }

    private final InputStreamReader userSettingDeleteReader = new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("sql/delete-all.sql")));
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BatchSQLExecutor executor;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private UserSettingService service;

    @BeforeEach
    public void clearUp() throws SQLException {
        executor.batchExecuteSqlFromFile(userSettingDeleteReader);
        identityService.setAuthenticatedUserId("demo");
    }

    @Test
    public void saveOrUpdateShould200AndReturnData() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/enhancement/user-setting")
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"userId\": \"test1\",\"preferenceJson\": {\"test1\": \"1\",\"test2\": \"3\",\"test3\": \"test\"}}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/enhancement/user-setting")
                                .headers(headers)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"userId\": \"test1\",\"preferenceJson\": {\"test1\": \"1\",\"test2\": \"3\",\"test3\": \"test\"}}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void queryForUserPreferenceShould200AndReturnData() throws Exception {

        UserSettingSaveDTO saveDto = new UserSettingSaveDTO();

        HashMap<String, Object> preference = new HashMap<>();
        preference.put("view", "history");
        saveDto.setUserId("demo");
        saveDto.setPreferenceJson(preference);
        service.saveOrUpdate(saveDto);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/enhancement/user-setting")
                                .headers(headers)
                                .param("userId", "demo")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.preference").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.preference.view").value("history"))
                .andDo(print());
    }
}
