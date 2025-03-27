package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Account_type;
import mg.projects.wallet.services.AccounTypeService;

/*
 * Creation account_type controller in locak
 */

@RestController
@RequestMapping("account_type")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class AccounTypeController extends CommonController<AccounTypeService, Account_type>{

    public AccounTypeController(AccounTypeService service) {
        super(service);
    }

}
