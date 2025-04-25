package mg.projects.wallet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import mg.projects.wallet.common.CommonRepository;
import mg.projects.wallet.dto.natives.VamountDTO;
import mg.projects.wallet.models.Credit;

public interface CreditRepo extends CommonRepository<Credit, String> {
    @Query(nativeQuery = true, 
    value =
"select customer_id, sum(amount) amount,account_id from v_list_credit where customer_id=:customer_id and (date_part('year', current_timestamp) = date_part('year', mois_du_credit)) and date_part('month', mois_du_credit) in (date_part('month', current_timestamp) - 1, date_part('month', current_timestamp) ) group by customer_id, account_id"
    )
    List<VamountDTO> selectCurrentCreditFromView(String customer_id);
}
