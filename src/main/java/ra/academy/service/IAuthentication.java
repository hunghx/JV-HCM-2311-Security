package ra.academy.service;

import ra.academy.model.dto.request.FormLogin;
import ra.academy.model.dto.response.JWTResponse;

public interface IAuthentication {
    JWTResponse login(FormLogin formLogin);
}
