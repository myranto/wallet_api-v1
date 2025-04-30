package mg.projects.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.format.ToJsonData;
import mg.projects.wallet.models.Account;
import mg.projects.wallet.services.AccountService;
import mg.projects.wallet.services.views.VamountService;
import mg.projects.wallet.visual.VamountDTO;

@RestController
@RequestMapping("account")
public class AccountController extends CommonController<AccountService, Account> {
    @Autowired
    private VamountService amountService;
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
    @GetMapping("/current_amount/{id}")
    public ResponseEntity<?> selectActualAmount(@PathVariable String id) {
        try {
            List<VamountDTO> list = amountService.getCurrentAmount(id);

            return ResponseEntity.ok(new ToJsonData<>(list, null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }
    // maka anle ammount farany by type_id(account_type)
    @GetMapping("/last/{id}")
    public ResponseEntity<?> selectbyLastAccount(@PathVariable String id) {
        try {
            List<Account> list = getService().findAccountValue(id);

            return ResponseEntity.ok(new ToJsonData<>(list, null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }
    

}
