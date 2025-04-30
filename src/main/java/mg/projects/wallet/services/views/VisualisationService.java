package mg.projects.wallet.services.views;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Visualisation> visualisationAccount(String customer) throws Exception {
        List<VamountDTO> chargeAmounts = chargeRepo.selectCurrentChargeFromView(customer);
        List<VamountDTO> creditAmounts = creditRepo.selectCurrentCreditFromView(customer);
        List<Account> accountAmounts = accountRepo.getManualSold(customer);
        List<VamountDTO> accountAmountDTO = VamountService.convertAccountToVamount(accountAmounts);
        List<Visualisation> result = new ArrayList<>();
        createVisual(creditAmounts, chargeAmounts, accountAmountDTO, result);
        return result;
    }

    private void createVisual(List<VamountDTO> credit, List<VamountDTO> charge, List<VamountDTO> currentAmount,
            List<Visualisation> result) throws Exception {
        Map<String, Visualisation> regroupment = regroupByDate(credit, charge, currentAmount);
    }

    private Map<String, Visualisation> regroupByDate(List<VamountDTO> credit, List<VamountDTO> charge,
            List<VamountDTO> currentAmount) throws Exception {
        Map<String, Visualisation> regroupment = new HashMap<>();

        Map<String, List<VamountDTO>> groupDcr = groupByAccount(credit);
        Map<String, List<VamountDTO>> groupDch = groupByAccount(charge);
        Map<String, List<VamountDTO>> groupDcurrentAmount = groupByAccount(currentAmount);
        
        Map<String, Map<String, List<VamountDTO>>> val = new HashMap<>();

        return regroupment;
    }
    private Map<String, List<VamountDTO>> groupByAccount(List<VamountDTO> list){
        Map<String, List<VamountDTO>> regroupment = new HashMap<>();
        for (VamountDTO vamountDTO : list) {
            String accountID = vamountDTO.getAccount_id();
            regroupment.compute(accountID, (key, dto) -> {
                if (dto == null) {
                    List<VamountDTO> renew = new ArrayList<>();
                    renew.add(vamountDTO);
                    return renew;
                }else{
                    dto.add(vamountDTO);
                    return dto;
                }
            });
        }
        return regroupment;
    }


    private void calculAmountByDate(){

    }

    private String extractYearAndMonth(Timestamp dates) throws Exception {
        if (dates == null)
            throw new Exception("No date to extract");
        LocalDateTime dateTime1 = dates.toLocalDateTime();
        int year = dateTime1.getYear();
        Month month = dateTime1.getMonth();

        return year + "-" + month;
    }

    /**
     * Vérifie si deux Timestamps sont dans la même année et le même mois.
     *
     * @param ts1 Le premier Timestamp.
     * @param ts2 Le second Timestamp.
     * @return true si l'année et le mois sont identiques, false sinon.
     */
    private boolean isSameYearAndMonth(Timestamp ts1, Timestamp ts2) {
        if (ts1 == null || ts2 == null) {
            // Gérer le cas où un Timestamp est null si nécessaire
            return false;
        }

        LocalDateTime dateTime1 = ts1.toLocalDateTime();
        LocalDateTime dateTime2 = ts2.toLocalDateTime();

        int year1 = dateTime1.getYear();
        Month month1 = dateTime1.getMonth();

        int year2 = dateTime2.getYear();
        Month month2 = dateTime2.getMonth();

        return year1 == year2 && month1 == month2;
    }

}
