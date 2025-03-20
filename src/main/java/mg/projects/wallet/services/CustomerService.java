package mg.projects.wallet.services;

import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;

@Service
public class CustomerService  extends CommonService<Customer, String, CustomerRepo>{

    public CustomerService(CustomerRepo jpa) {
        super(jpa);
    }

}
