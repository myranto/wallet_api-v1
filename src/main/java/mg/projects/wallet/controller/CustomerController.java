package mg.projects.wallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.projects.wallet.common.CommonController;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.services.CustomerService;
@RestController
@RequestMapping("/customer")
public class CustomerController extends CommonController<CustomerService, Customer>{

    public CustomerController(CustomerService service) {
        super(service);
    }

}
