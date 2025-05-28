package com.diettracker.backend.login;

import com.diettracker.backend.myappuser.MyAppUser;
import com.diettracker.backend.myappuser.MyAppUserService;
import com.diettracker.backend.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/req")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyAppUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getUsername() == null || request.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nazwa użytkownika i hasło są wymagane");
        }

        Optional<MyAppUser> userOpt = userService.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nieprawidłowa nazwa użytkownika lub hasło");
        }

        MyAppUser user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nieprawidłowa nazwa użytkownika lub hasło");
        }

        if (!user.isVerified()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Konto nie jest zweryfikowane");
        }

        String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
