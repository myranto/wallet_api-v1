package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Charge;
import mg.projects.wallet.services.ChargeService;

@RestController
@RequestMapping("charge")
public class ChargeController extends CommonController<ChargeService, Charge> {

    public ChargeController(ChargeService service) {
        super(service);
    }

}
