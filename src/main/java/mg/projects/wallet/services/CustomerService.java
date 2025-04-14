package mg.projects.wallet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mg.projects.wallet.common.CommonService;
import mg.projects.wallet.models.Customer;
import mg.projects.wallet.repository.CustomerRepo;

@Service
public class CustomerService extends CommonService<Customer, String, CustomerRepo> {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepo jpa) {
        super(jpa);
    }

    @Override
    public Customer save(Customer model) {
        if (model.getPassword() == null) {
            System.out.println(model.getMail() +"'s password = "+model.getName().trim());
            model.setPassword(passwordEncoder.encode(model.getName().trim()));
        }
        return super.save(model);
    }

}
