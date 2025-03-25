package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Account;
import mg.projects.wallet.services.AccountService;

@RestController
@RequestMapping("account")
public class AccountController extends CommonController<AccountService, Account> {
    public AccountController(AccountService service) {
        super(service);
    }
}
