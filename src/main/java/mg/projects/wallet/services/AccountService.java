package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Account;
import mg.projects.wallet.repository.AccountRepo;

@Service
public class AccountService extends CommonService<Account, String, AccountRepo>{

    public AccountService(AccountRepo jpa) {
        super(jpa);
    }

}
