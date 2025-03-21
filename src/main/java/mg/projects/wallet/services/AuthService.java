package mg.projects.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;

@Service
public class AuthService {
    @Autowired
    private CustomerRepo repo;

    public Customer checkPassword(AuthDTO user) throws Exception{
            Customer finded = repo.findOneByMail(user.getMail()).orElseThrow(()->new Exception("Identifiant ou mot de passe incorrect"));
            if (!finded.getPassword().equals(user.getPassword())) {
                throw new Exception("Identifiant ou mot de passe incorrect");
            }
            return finded;
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
}
