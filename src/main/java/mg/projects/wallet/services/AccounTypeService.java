package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Account_type;
import mg.projects.wallet.repository.AccounTypeRepo;
/*
 * Création Service account_Type in local
 */
@Service
public class AccounTypeService extends CommonService<Account_type, String, AccounTypeRepo> {

    public AccounTypeService(AccounTypeRepo jpa) {
        super(jpa);
    }
    
}
