package com.eorion.bo.enhancement.usersetting.adapter.inbound;

import com.eorion.bo.enhancement.usersetting.domain.dto.inbound.UserSettingSaveDTO;
import com.eorion.bo.enhancement.usersetting.domain.dto.outbound.UserSettingDTO;
import com.eorion.bo.enhancement.usersetting.domain.exception.InsertFailedException;
import com.eorion.bo.enhancement.usersetting.domain.exception.UpdateFailedException;
import com.eorion.bo.enhancement.usersetting.service.UserSettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/enhancement/user-setting")
public class UserSettingController {

    private final UserSettingService userSettingService;

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdate(@RequestBody UserSettingSaveDTO setting) throws UpdateFailedException, InsertFailedException {

        return userSettingService.saveOrUpdate(setting);
    }

    @GetMapping()
    public ResponseEntity<UserSettingDTO> getSettingByUserId(@RequestParam("userId") String userId) {
        return userSettingService.getByUserId(userId);
    }

}
