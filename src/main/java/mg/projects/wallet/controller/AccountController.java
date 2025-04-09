package mg.projects.wallet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.format.ToJsonData;
import mg.projects.wallet.models.Account;
import mg.projects.wallet.services.AccountService;

@RestController
@RequestMapping("account")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class AccountController extends CommonController<AccountService, Account> {
    public AccountController(AccountService service) {
        super(service);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<?> selectbyCustomer(@PathVariable String id) {
        try {
            List<Account> list = getService().findByCustomer(id);

            return ResponseEntity.ok(new ToJsonData<>(list, null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
