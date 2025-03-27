package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Account;
import mg.projects.wallet.services.AccountService;

@RestController
@RequestMapping("account")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class AccountController extends CommonController<AccountService, Account> {
    public AccountController(AccountService service) {
        super(service);
    }
}
