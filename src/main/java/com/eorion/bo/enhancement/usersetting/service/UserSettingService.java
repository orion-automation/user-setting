package com.eorion.bo.enhancement.usersetting.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.eorion.bo.enhancement.usersetting.adapter.outbound.UserSettingRepository;
import com.eorion.bo.enhancement.usersetting.domain.dto.inbound.UserSettingSaveDTO;
import com.eorion.bo.enhancement.usersetting.domain.dto.outbound.IdDTO;
import com.eorion.bo.enhancement.usersetting.domain.dto.outbound.UserSettingDTO;
import com.eorion.bo.enhancement.usersetting.domain.entity.UserSetting;
import com.eorion.bo.enhancement.usersetting.domain.exception.InsertFailedException;
import com.eorion.bo.enhancement.usersetting.domain.exception.UpdateFailedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class UserSettingService {

    private final UserSettingRepository repository;

    public ResponseEntity<UserSettingDTO> getByUserId(String userId) {
        var userSetting = repository.getOne(new LambdaQueryWrapper<UserSetting>().eq(UserSetting::getUserId, userId));
        if (Objects.nonNull(userSetting)) {
            return ResponseEntity.ok(UserSettingDTO.fromEntity(userSetting));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> saveOrUpdate(UserSettingSaveDTO saveDto) throws UpdateFailedException, InsertFailedException {
        var dbSetting = repository.getOne(new LambdaQueryWrapper<UserSetting>().eq(UserSetting::getUserId, saveDto.getUserId()));
        IdDTO<Integer> idDTO = new IdDTO<>();
        if (dbSetting != null) {
            try {
                repository.updateByUserId(saveDto);
                idDTO.setId(dbSetting.getId());
            } catch (RuntimeException e) {
                log.error("update setting error: {}", e.getMessage());
                throw new UpdateFailedException("更新失败！");
            }
        } else {
            try {
                repository.saveUserSetting(saveDto);
                idDTO.setId(saveDto.getId());
            } catch (RuntimeException e) {
                log.error("save setting error {}", e.getMessage());
                throw new InsertFailedException("保存失败！");
            }
        }
        return ResponseEntity.ok(idDTO);
    }
}
