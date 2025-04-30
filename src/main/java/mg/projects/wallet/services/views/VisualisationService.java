package mg.projects.wallet.services.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.projects.wallet.models.Account;
import mg.projects.wallet.repository.AccountRepo;
import mg.projects.wallet.repository.ChargeRepo;
import mg.projects.wallet.repository.CreditRepo;
import mg.projects.wallet.visual.VamountDTO;
import mg.projects.wallet.visual.Visualisation;

@Service
public class VisualisationService {
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private ChargeRepo chargeRepo;
    @Autowired
    private AccountRepo accountRepo;
    public List<Visualisation> visualisationAccount(String customer){
        List<VamountDTO> chargeAmounts = chargeRepo.selectCurrentChargeFromView(customer);
        List<VamountDTO> creditAmounts = creditRepo.selectCurrentCreditFromView(customer);
        List<Account> accountAmounts = accountRepo.getManualSold(customer);
        List<Visualisation> result = new ArrayList<>();
        return result;
    }
}
