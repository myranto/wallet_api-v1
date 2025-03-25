package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Credit;
import mg.projects.wallet.repository.CreditRepo;

@Service
public class CreditService extends CommonService<Credit, String, CreditRepo> {

    public CreditService(CreditRepo jpa) {
        super(jpa);
    }

}
