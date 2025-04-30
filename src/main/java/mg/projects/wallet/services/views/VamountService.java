package mg.projects.wallet.services.views;

import java.math.BigDecimal;
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

@Service
public class VamountService {
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private ChargeRepo chargeRepo;
    @Autowired
    private AccountRepo accountRepo;

    public List<VamountDTO> getCurrentAmount(String customer) {
        List<VamountDTO> chargeAmounts = chargeRepo.selectCurrentChargeFromView(customer);
        List<VamountDTO> creditAmounts = creditRepo.selectCurrentCreditFromView(customer);
        List<Account> accountAmounts = accountRepo.getManualSold(customer);
        List<VamountDTO> sumCreditAccount = calculateAmount(creditAmounts, convertAccountToVamount(accountAmounts), "sum");
        List<VamountDTO> diffCreditAccountAndCharge = calculateAmount(sumCreditAccount, chargeAmounts, "diff");
        return diffCreditAccountAndCharge;
    }

    private List<VamountDTO> convertAccountToVamount(List<Account> accounts) {
        List<VamountDTO> result = new ArrayList<>();
        for (Account row : accounts) {
            VamountDTO newDto = new VamountDTO();
            newDto.setCustomer_id(row.getCustomer_id());
            newDto.setAccount_id(row.getType_id());
            newDto.setAmount(row.getCurrentamount());
            result.add(newDto);
        }
        return result;
    }

    private List<VamountDTO> calculateAmount(List<VamountDTO> creditAmounts, List<VamountDTO> accountAmounts,
            String operation) {
        Map<String, VamountDTO> aggregatedAmounts = new HashMap<>();

        // 1. Ajouter les montants de creditAmounts à la Map
        for (VamountDTO creditDto : creditAmounts) {
            String accountId = creditDto.getAccount_id();
            BigDecimal amount = creditDto.getAmount() != null ? creditDto.getAmount() : BigDecimal.ZERO; 

            // Utiliser compute pour gérer l'ajout ou la mise à jour
            aggregatedAmounts.compute(accountId, (key, existingDto) -> {
                if (existingDto == null) {
                    // Premier montant pour cet accountId
                    VamountDTO newDto = new VamountDTO();
                    newDto.setCustomer_id(creditDto.getCustomer_id()); // Conserver l'ID client
                    newDto.setAccount_id(accountId);
                    newDto.setAmount(amount);
                    return newDto;
                } else {
                    // AccountId déjà présent, ajouter le montant
                    existingDto.setAmount(existingDto.getAmount().add(amount));
                    return existingDto;
                }
            });
        }

        // 2. Ajouter/Fusionner les montants de accountAmounts à la Map
        for (VamountDTO account : accountAmounts) {
            // Convertir le type Account en String pour la comparaison avec account_id
            String accountId = account.getAccount_id() != null ? account.getAccount_id().toString() : null; 
            BigDecimal currentAmount = account.getAmount() != null ? account.getAmount()
                    : BigDecimal.ZERO; 
            String customerId = account.getCustomer_id(); // Récupérer l'ID client de l'Account

            if (accountId != null) { 
                aggregatedAmounts.compute(accountId, (key, existingDto) -> {
                    if (existingDto == null) {
                        // Cette clé accountId (type) n'était pas dans creditAmounts initialement.
                        // Créer une nouvelle entrée pour inclure ce montant.
                        VamountDTO newDto = new VamountDTO();
                        newDto.setCustomer_id(customerId);
                        newDto.setAccount_id(accountId);
                        newDto.setAmount(currentAmount);
                        return newDto;
                    } else {
                        if (operation.equals("sum")) {
                            existingDto.setAmount(existingDto.getAmount().add(currentAmount));
                        } else {
                            existingDto.setAmount(existingDto.getAmount().subtract(currentAmount));
                        }
                        return existingDto;
                    }
                });
            }
        }

        // 3. Convertir les valeurs de la Map en une List de VamountDTO
        return new ArrayList<>(aggregatedAmounts.values());
    }
}
