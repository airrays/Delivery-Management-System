package exception;

import io.bootify.delivery_management_system.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations={RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    //handle duplicated account name entry
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception){
        log.error(exception.getMessage());
        if(exception.getMessage().contains("Duplicate entry")){
            String[] splite=exception.getMessage().split(" ");
            String msg=splite[2]+"Account Already Exist";
            return R.error(msg);
        }
        return R.error("Unknow Error");
    }

//    ErrorResponse illegalArgumentResponse = new ErrorResponse(new IllegalArgumentException("Illegal arguments found!"));
//    ErrorResponse resourceNotFoundResponse = new ErrorResponse(new ResourceNotFoundException("Sorry, the resourse not Found "));
//    @ExceptionHandler(value =Exception.class)
//    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e){
//        if(e instanceof IllegalArgumentException){
//            return ResponseEntity.status(400).body(illegalArgumentResponse);
//        }else if(e instanceof ResourceNotFoundException){
//            return ResponseEntity.status(404).body(resourceNotFoundResponse);
//        }
//        return null;
//    }

    /***
     * Elegant way for exception handlers
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleAppException(BaseException ex, HttpServletRequest request){
        ErrorResponse representation = new ErrorResponse(ex,request.getRequestURI());
        return new ResponseEntity<>(representation,new HttpHeaders(),ex.getError().getStatus());
    }
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorReponse = new ErrorResponse(ex, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> categoryDeleteExceptionHandler(CustomException exception){
        log.error(exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(exception);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse.getMessage());
    }
}
