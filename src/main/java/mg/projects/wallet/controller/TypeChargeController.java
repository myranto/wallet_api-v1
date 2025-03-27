package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.TypeCharge;
import mg.projects.wallet.services.TypeChargeService;

@RestController
@RequestMapping("type_charge")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TypeChargeController extends CommonController<TypeChargeService, TypeCharge> {

    public TypeChargeController(TypeChargeService service) {
        super(service);
    }

}
