package lt.java.exam.service;

import lombok.RequiredArgsConstructor;

import lt.java.exam.exception.UserExistsException;
import lt.java.exam.exception.UserNotExistsException;
import lt.java.exam.model.User;
import lt.java.exam.model.UserAuth;
import lt.java.exam.model.request.LoginRequest;
import lt.java.exam.model.request.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    public final UserService userService;

    public final JwtService jwtService;
    public final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null == authentication) {
            return null;
        }

        if (!authentication.isAuthenticated()) {
            return null;
        }

        return (User) authentication.getPrincipal();
    }

    public UserAuth login(LoginRequest request) {
        User existingUser = userService.findByEmail(request.getEmail());

        if (null == existingUser) {
            throw new UserNotExistsException("Email not exists");
        }

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (InternalAuthenticationServiceException e) {
            return null;
        }

        if (null == authentication) {
            return null;
        }

        if (!authentication.isAuthenticated()) {
            return null;
        }

        return getUserAuth(
                userService.findByEmail(request.getEmail())
        );
    }

    public UserAuth register(RegisterRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new UserExistsException("User already exists");
        }

        String password = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(password)
                .role(User.Role.USER)
                .build();

        userService.save(user);

        return getUserAuth(user);
    }

    public UserAuth getUserAuth(User user) {
        return UserAuth.builder()
                .token(jwtService.generateToken(user))
                .user(user)
                .build();
    }
}
