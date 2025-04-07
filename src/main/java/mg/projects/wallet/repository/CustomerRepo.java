package mg.projects.wallet.repository;

import java.util.Optional;


import mg.projects.wallet.common.CommonRepository;
import mg.projects.wallet.models.Customer;


public interface CustomerRepo extends CommonRepository<Customer, String>  {
    Optional<Customer> findOneByMail(String mail);
}
