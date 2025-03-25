package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Charge;
import mg.projects.wallet.repository.ChargeRepo;

@Service
public class ChargeService extends CommonService<Charge, String, ChargeRepo>{

    public ChargeService(ChargeRepo jpa) {
        super(jpa);
    }

}
