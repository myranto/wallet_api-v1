package mg.projects.wallet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import mg.projects.wallet.common.CommonRepository;
import mg.projects.wallet.models.Account;

public interface AccountRepo extends CommonRepository<Account, String> {
    @Query("select a from Account a where a.status=0 and a.customer_id = :customer_id")
    List<Account> findByCustomer_id(String customer_id);

    @Query("SELECT a\r\n" + //
                " FROM Account a\r\n" + //
                " WHERE a.customer_id = :customer_id\r\n" + //
                " and a.status=0\r\n" + //
                " AND a.dateamount = (\r\n" + //
                "    SELECT MAX(dateamount)\r\n" + //
                "    FROM Account sub_a\r\n" + //
                "    WHERE sub_a.customer_id = :customer_id\r\n" + //
                "    AND sub_a.type_id = a.type_id\r\n" + //
                ")")
    List<Account> findLastAccountByAccountID(String customer_id);

    @Query(nativeQuery = true, value = "select * from get_manual_solde(:customer_id)")
    List<Account> getManualSold(String customer_id);
}