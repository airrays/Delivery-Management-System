package exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public abstract class BaseException extends RuntimeException{
    private final ErrorCode error;
    private final HashMap<String,Object> data=new HashMap<>();
    public BaseException(ErrorCode error, Map<String,Object> data){
        super(error.getMessage());
        this.error=error;
        if(!ObjectUtils.isEmpty(data)){
            this.data.putAll(data);
        }
    }

    protected BaseException(ErrorCode error,Map<String,Object>data, Throwable cause){
        super(error.getMessage(),cause);
        this.error=error;
        if(!ObjectUtils.isEmpty(data)){
            this.data.putAll(data);
        }
    }
}
