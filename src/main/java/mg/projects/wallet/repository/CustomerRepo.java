package mg.projects.wallet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projects.wallet.models.Customer;


public interface CustomerRepo extends JpaRepository<Customer, String>  {
    Optional<Customer> findOneByMail(String mail);
    
}
