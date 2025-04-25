package mg.projects.wallet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.dto.natives.VamountDTO;
import mg.projects.wallet.format.ToJsonData;
import mg.projects.wallet.models.Credit;
import mg.projects.wallet.services.CreditService;

@RestController
@RequestMapping("credit")
class CreditController extends CommonController<CreditService, Credit>{

    public CreditController(CreditService service) {
        super(service);
    }
    @GetMapping("/current")
    public ResponseEntity<?> selectCurrentCredit() {
        try {
            List<VamountDTO> list = getService().selectCurrCredits();

            return ResponseEntity.ok(new ToJsonData<>(list, null));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null, e.getMessage()), org.springframework.http.HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
