package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Charge;
import mg.projects.wallet.services.ChargeService;

@RestController
@RequestMapping("charge")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class ChargeController extends CommonController<ChargeService, Charge> {

    public ChargeController(ChargeService service) {
        super(service);
    }

}
