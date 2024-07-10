package ra.academy.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ra.academy.model.entity.Role;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JWTResponse {
    private Integer id;
    private String fullName;
    private Set<Role> roles;
    private final String type = "Bearer Token";
    private String accessToken;
}
