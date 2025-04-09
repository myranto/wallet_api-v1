package mg.projects.wallet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import mg.projects.wallet.common.CommonRepository;
import mg.projects.wallet.models.Account;

public interface AccountRepo extends CommonRepository<Account, String> {
    @Query("select a from Account a where a.status=0 and a.customer_id = :customer_id")
    List<Account> findByCustomer_id(String customer_id);
}