package mg.projects.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.format.ToJsonData;
import mg.projects.wallet.services.AuthService;

// classe controller servant pour l'authentication
@RestController
@RequestMapping("auth")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
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
    /* 
     * Controller for forget password in local
     */
    @PostMapping("/reset/password")
    public ResponseEntity<?> resetPassword(@RequestBody AuthDTO model) {
        try {
            return ResponseEntity.ok(new ToJsonData<>(service.resetPassword(model), null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }
    
}
