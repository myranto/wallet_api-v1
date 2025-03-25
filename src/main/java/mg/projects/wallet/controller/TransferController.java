package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Transfer;
import mg.projects.wallet.services.TransferService;

@RestController
@RequestMapping("transfer")
public class TransferController extends CommonController<TransferService, Transfer>{

    public TransferController(TransferService service) {
        super(service);
    }

}
