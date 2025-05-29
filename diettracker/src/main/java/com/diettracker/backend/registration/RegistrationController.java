package com.diettracker.backend.registration;
import com.diettracker.backend.myappuser.MyAppUser;
import com.diettracker.backend.login.LoginRequest;
import com.diettracker.backend.myappuser.MyAppUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/req")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Registration", description = "Rejestracja nowego użytkownika")

public class RegistrationController {

    @Autowired
    private MyAppUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody LoginRequest request) {
        try {
            if (request.getUsername() == null || request.getPassword() == null) {
                return new ResponseEntity<>("Brak wymaganych pól", HttpStatus.BAD_REQUEST);
            }

            if (userService.findByUsername(request.getUsername()).isPresent()) {
                return new ResponseEntity<>("Użytkownik o podanej nazwie już istnieje", HttpStatus.BAD_REQUEST);
            }

            MyAppUser user = new MyAppUser();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setVerified(true);
            userService.save(user);

            return new ResponseEntity<>("Rejestracja zakończona sukcesem!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Błąd podczas rejestracji: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}