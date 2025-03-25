package mg.projects.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.projects.wallet.dto.AuthDTO;
import mg.projects.wallet.dto.CustomerDTO;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;
// classe service servant pour l'authentication
@Service
public class AuthService {
    @Autowired
    private CustomerRepo repo;

    public CustomerDTO checkPassword(AuthDTO user) throws Exception{
            Customer finded = repo.findOneByMail(user.getMail()).orElseThrow(()->new Exception("Identifiant ou mot de passe incorrect"));
            if (!finded.getPassword().equals(user.getPassword())) {
                throw new Exception("Identifiant ou mot de passe incorrect");
            }
            return (CustomerDTO) finded.EntityToDTO();
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
