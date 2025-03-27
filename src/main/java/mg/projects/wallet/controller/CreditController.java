package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Credit;
import mg.projects.wallet.services.CreditService;

@RestController
@RequestMapping("credit")
public @CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
class CreditController extends CommonController<CreditService, Credit>{

    public CreditController(CreditService service) {
        super(service);
    }

}
