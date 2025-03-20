package mg.projects.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.format.ToJsonData;
import mg.projects.wallet.services.AuthService;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody AuthDTO model) {
        try {
            return ResponseEntity.ok(new ToJsonData<>(service.checkPassword(model), null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.UNAUTHORIZED);
        }
    }
}
