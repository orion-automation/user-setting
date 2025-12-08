package com.eorion.bo.enhancement.usersetting.adapter.inbound;

import com.eorion.bo.enhancement.usersetting.domain.dto.inbound.UserSettingSaveDTO;
import com.eorion.bo.enhancement.usersetting.domain.dto.outbound.IdDTO;
import com.eorion.bo.enhancement.usersetting.domain.dto.outbound.UserSettingDTO;
import com.eorion.bo.enhancement.usersetting.domain.exception.InsertFailedException;
import com.eorion.bo.enhancement.usersetting.domain.exception.UpdateFailedException;
import com.eorion.bo.enhancement.usersetting.service.UserSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enhancement/user-setting")
@Tag(name = "User Setting")
public class UserSettingController {

    private final UserSettingService userSettingService;

    public UserSettingController(UserSettingService userSettingService) {
        this.userSettingService = userSettingService;
    }

    @Operation(summary = "Create or update a user setting entity",
            description = "If provided \"userId\" existed, the operation is updating, otherwise is creating.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = IdDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error when creating or updating")
    })
    @PostMapping
    public ResponseEntity<?> saveOrUpdate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User Setting to create or update", required = true,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserSettingSaveDTO.class),
                            examples = @ExampleObject(value = """
                                    {"userId": "test1","preferenceJson": {"test1": "1","test2": "3","test3": "test"}}""")))
            @RequestBody @Valid UserSettingSaveDTO setting) throws UpdateFailedException, InsertFailedException {
        return userSettingService.saveOrUpdate(setting);
    }

    @Operation(summary = "Get User's setting information by id")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserSettingDTO.class)))
    )
    @GetMapping
    public ResponseEntity<UserSettingDTO> getSettingByUserId(
            @Parameter(
                    description = "User ID",
                    example = "demo", required = true,
                    schema = @Schema(type = "string", maxLength = 64)
            )
            @RequestParam("userId") String userId) {
        return userSettingService.getByUserId(userId);
    }

}
