package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.TypeOperation;
import mg.projects.wallet.services.TypeOperationService;

@RestController
@RequestMapping("type_operation")
public class TypeOperationController extends CommonController<TypeOperationService, TypeOperation> {

    public TypeOperationController(TypeOperationService service) {
        super(service);
    }

}
