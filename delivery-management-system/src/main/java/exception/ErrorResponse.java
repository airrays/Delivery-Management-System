package exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Slf4j
public class ErrorResponse {
    private String message;
    private String errorTypeName;

    private int code;
    private int status;
    private String path;
    private Instant timestamp;
    private HashMap<String, Object>data=new HashMap<String, Object>();

    public ErrorResponse(){}

    public ErrorResponse(Exception e){
        this(e.getClass().getName(), e.getMessage());
    }
    public ErrorResponse(BaseException ex, String path) {
        this(ex.getError().getCode(), ex.getError().getStatus().value(), ex.getError().getMessage(), path, ex.getData());
    }

    public ErrorResponse(String errorTypeName,String message){
        this.errorTypeName=errorTypeName;
        this.message=message;
    }

    public ErrorResponse(int code, int status, String message, String path, Map<String, Object> data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }
}
