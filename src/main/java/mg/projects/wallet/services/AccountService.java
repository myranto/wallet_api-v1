package mg.projects.wallet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Account;
import mg.projects.wallet.repository.AccountRepo;

@Service
public class AccountService extends CommonService<Account, String, AccountRepo>{

    public AccountService(AccountRepo jpa) {
        super(jpa);
    }
    public List<Account> findByCustomer(String customer){
        return getJpa().findByCustomer_id(customer);
    }
    public List<Account> findAccountValue(String customer){
        return getJpa().findLastAccountByAccountID(customer);
    }

}
