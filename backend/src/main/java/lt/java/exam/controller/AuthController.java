package lt.java.exam.controller;

import lombok.RequiredArgsConstructor;

import lt.java.exam.model.User;
import lt.java.exam.model.UserAuth;
import lt.java.exam.model.request.LoginRequest;
import lt.java.exam.model.request.RegisterRequest;
import lt.java.exam.service.AuthService;
import lt.java.exam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    public final UserService userService;
    public final AuthService authService;

    @GetMapping
    public ResponseEntity<User> currentUser() {
        User user = authService.currentUser();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuth> loginUser(@RequestBody LoginRequest loginRequest) {
        UserAuth userAuth = authService.login(loginRequest);

        return new ResponseEntity<>(userAuth, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuth> registerUser(@RequestBody RegisterRequest registerRequest) {
        UserAuth userAuth = authService.register(registerRequest);

        return new ResponseEntity<>(userAuth, HttpStatus.OK);
    }
}
