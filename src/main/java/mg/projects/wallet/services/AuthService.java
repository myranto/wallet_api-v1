package mg.projects.wallet.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.dto.CustomerDTO;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;
import mg.projects.wallet.services.security.jwt.JWTUtility;
// classe service servant pour l'authentication
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private CustomerRepo repo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerDTO checkPassword(AuthDTO user) throws Exception{
            Customer finded = repo.findOneByMail(user.getMail()).orElseThrow(()->new Exception("Identifiant ou mot de passe incorrect"));
            if (!passwordEncoder.matches(user.getPassword(), finded.getPassword())) {
                throw new Exception("Identifiant ou mot de passe incorrect");
            }
            // if (!finded.getPassword().equals(user.getPassword())) {
            //     throw new Exception("Identifiant ou mot de passe incorrect");
            // }
            CustomerDTO logged = (CustomerDTO) finded.EntityToDTO();
            String token = JWTUtility.generateToken(user.getMail());
            logged.setToken(token);
            return logged;
    }
    /* 
     * Forget password in local
     */
    public String resetPassword(AuthDTO user) throws Exception{
        Customer finded = repo.findOneByMail(user.getMail()).orElseThrow(()->new Exception("Email: "+user.getMail()+" introuvable"));
        if (user.getPassword().equals(user.getPwd())) {
            finded.setPassword(user.getPassword());
            repo.save(finded);
            return "Modification réussi";
        }
        throw new Exception("Veuillez mettre des mots de passes corrects");
    }
    /*
     * end forget password
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Person user = personRepo.findPersonByMail(username);
        Customer user = repo.findOneByMail(username).orElseThrow(()->new UsernameNotFoundException("Identifiant ou mot de passe incorrect"));
        return new User(user.getMail(), user.getPassword(),
                new ArrayList<>());
    }
}
