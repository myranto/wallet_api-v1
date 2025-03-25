package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.TypeOperation;
import mg.projects.wallet.repository.TypeOperationRepo;

@Service
public class TypeOperationService extends CommonService<TypeOperation, String, TypeOperationRepo>{

    public TypeOperationService(TypeOperationRepo jpa) {
        super(jpa);
    }

}
