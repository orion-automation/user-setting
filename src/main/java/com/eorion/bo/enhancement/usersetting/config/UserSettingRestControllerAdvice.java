package com.eorion.bo.enhancement.usersetting.config;

import com.eorion.bo.enhancement.usersetting.domain.commom.ErrorMessage;
import com.eorion.bo.enhancement.usersetting.domain.exception.InsertFailedException;
import com.eorion.bo.enhancement.usersetting.domain.exception.UpdateFailedException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class UserSettingRestControllerAdvice {
    /**
     * InsertFailedException handler
     */
    @ExceptionHandler(InsertFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "insert failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    })
    @ResponseBody
    public ErrorMessage insertFailedExceptionHandler(InsertFailedException e) {
        log.error(e.getMessage());
        return new ErrorMessage(e.getMessage());
    }

    /**
     * 修改失败
     */
    @ExceptionHandler(UpdateFailedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "500",
                    description = "Update failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    })
    @ResponseBody
    public ErrorMessage updateFailed(UpdateFailedException e) {
        log.error(e.getMessage());
        return new ErrorMessage(e.getMessage());
    }


}
