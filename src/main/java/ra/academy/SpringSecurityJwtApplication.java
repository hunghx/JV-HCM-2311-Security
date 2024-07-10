package ra.academy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ra.academy.model.entity.Role;
import ra.academy.model.entity.RoleName;
import ra.academy.model.entity.User;
import ra.academy.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner runner(PasswordEncoder passwordEncoder, UserRepository userRepository){
//        return args -> {
//            Role admin = new Role(null, RoleName.ROLE_ADMIN);
//            Role user = new Role(null, RoleName.ROLE_USER);
//            Role mod = new Role(null, RoleName.ROLE_MODERATOR);
//            Set<Role> roles = new HashSet<>();
//            roles.add(admin);
//            User u1= new User(null,"admin123",passwordEncoder.encode("admin123"),"admin",null,"admin@gmail.com",null,false,false,roles);
//            roles=new HashSet<>();
//            roles.add(user);
//            User u2= new User(null,"hunghx",passwordEncoder.encode("123456"),"hung","0984865435","hung@gmail.com","0984865435",false,false,roles);
//            roles=new HashSet<>();
//            roles.add(mod);
//            User u3= new User(null,"sontx",passwordEncoder.encode("123456"),"son","0984757456","son@gmail.com","0984757456",false,false,roles);
//            userRepository.save(u1);
//            userRepository.save(u2);
//            userRepository.save(u3);
//        };
//    }
}
