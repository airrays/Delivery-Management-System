package exception;

import java.util.Map;

public class ResourceNotFoundException extends BaseException{
    private String message;

    public ResourceNotFoundException(String message,Map<String, Object> data){
        super(ErrorCode.RESOURCE_NOT_FOUND,data);
        this.message=message;
    }
    public ResourceNotFoundException(Map<String, Object> data){
        super(ErrorCode.RESOURCE_NOT_FOUND,data);
    }

    @Override
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }

}
