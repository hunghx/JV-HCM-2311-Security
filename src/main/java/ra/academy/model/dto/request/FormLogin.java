package ra.academy.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FormLogin {
    @NotBlank(message = "Khong duoc de trong")
    private  String username;
    @NotBlank(message = "Khong duoc de trong")
    private String password;
}
