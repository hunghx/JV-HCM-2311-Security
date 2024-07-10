package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.dto.request.FormLogin;
import ra.academy.service.IAuthentication;

@RestController
@RequestMapping("/api.com/v1/auth")
public class AuthController {
    @Autowired
    private IAuthentication authentication;
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody FormLogin formLogin){
        return new ResponseEntity<>(authentication.login(formLogin), HttpStatus.OK);
    }
}
