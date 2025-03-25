package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.TypeCharge;
import mg.projects.wallet.repository.TypeChargeRepo;

@Service
public class TypeChargeService extends CommonService<TypeCharge, String, TypeChargeRepo> {

    public TypeChargeService(TypeChargeRepo jpa) {
        super(jpa);
    }

}
