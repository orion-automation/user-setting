package com.eorion.bo.enhancement.usersetting;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
public class UserSettingAutoConfiguration {
    @Bean
    public GroupedOpenApi userSettingGroup() {
        return GroupedOpenApi.builder()
                .group("user-setting")
                .packagesToScan("com.eorion.bo.enhancement.usersetting.adapter.inbound")
                .pathsToMatch("/enhancement/user-setting/**")
                .build();
    }
}
