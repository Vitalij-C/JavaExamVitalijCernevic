package lt.java.exam.config;

import lombok.RequiredArgsConstructor;

import lt.java.exam.model.User;
import lt.java.exam.persistance.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDefault implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        createAdmin();
        createUser();
    }

    public void createAdmin() {
        User user = User.builder()
                .email("admin@mail.lt")
                .password(passwordEncoder.encode("password"))
                .role(User.Role.ADMIN)
                .build();

        if (null == userRepository.findByEmailIgnoreCase(user.getEmail())) {
            userRepository.save(user);
        }
    }

    public void createUser() {
        User user = User.builder()
                .email("user@mail.lt")
                .password(passwordEncoder.encode("password"))
                .role(User.Role.USER)
                .build();

        if (null == userRepository.findByEmailIgnoreCase(user.getEmail())) {
            userRepository.save(user);
        }
    }
}
