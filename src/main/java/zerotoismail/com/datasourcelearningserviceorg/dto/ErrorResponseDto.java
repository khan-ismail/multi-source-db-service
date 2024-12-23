package zerotoismail.com.datasourcelearningserviceorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

public class ErrorResponseDto {
    private String apiPath;
    private HttpStatus errorCode;
    private String message;
    private Long time;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String message, Long time) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.message = message;
        this.time = time;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
