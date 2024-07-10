package ra.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.academy.exception.UserNameOrPasswordInvalidException;
import ra.academy.model.dto.request.FormLogin;
import ra.academy.model.dto.response.JWTResponse;
import ra.academy.model.entity.User;
import ra.academy.repository.UserRepository;
import ra.academy.security.jwt.JwtProvider;

import javax.naming.AuthenticationException;

@Service
public class AuthenticationImpl implements IAuthentication{
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public JWTResponse login(FormLogin formLogin) {
        // kiê tra xem username và pass co khớp ko

        try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(formLogin.getUsername(),formLogin.getPassword()));
        }catch (Exception e){
            throw new UserNameOrPasswordInvalidException("username or pass incorrect");
        }
        User user = userRepository.findByUsernameOrEmailOrPhone(formLogin.getUsername())
                .orElseThrow(() ->new UsernameNotFoundException("Username not found"));
        // trả về cho người dùng các thông tin cần thiết
        return JWTResponse.builder()
                .id(user.getId())
                .roles(user.getRoles())
                .fullName(user.getFullName())
                .accessToken(jwtProvider.generateToken(user.getUsername()))
                .build();
    }
}
