package mg.projects.wallet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.dto.natives.VamountDTO;
import mg.projects.wallet.models.Credit;
import mg.projects.wallet.repository.CreditRepo;

@Service
public class CreditService extends CommonService<Credit, String, CreditRepo> {

    public CreditService(CreditRepo jpa) {
        super(jpa);
    }
    public List<VamountDTO> selectCurrCredits(){
        return getJpa().selectCurrentCreditFromView("CUS00001");
    }

}
