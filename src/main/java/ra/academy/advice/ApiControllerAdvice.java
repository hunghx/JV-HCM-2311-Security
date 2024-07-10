package ra.academy.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.academy.exception.UserNameOrPasswordInvalidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(UserNameOrPasswordInvalidException.class)
    public ResponseEntity<?> handlerLogin(UserNameOrPasswordInvalidException e){
        Map<String , Object> map = new HashMap<>();
        map.put("code","400");
        map.put("error", HttpStatus.BAD_REQUEST);
        map.put("message",e.getMessage());
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }

}
