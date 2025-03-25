package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Transfer;
import mg.projects.wallet.repository.TransferRepo;

@Service
public class TransferService extends CommonService<Transfer, String, TransferRepo>{

    public TransferService(TransferRepo jpa) {
        super(jpa);
    }

}
