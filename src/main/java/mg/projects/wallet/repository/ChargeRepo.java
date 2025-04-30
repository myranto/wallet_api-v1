package mg.projects.wallet.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import mg.projects.wallet.common.CommonRepository;
import mg.projects.wallet.models.Charge;
import mg.projects.wallet.visual.VamountDTO;

public interface ChargeRepo extends CommonRepository<Charge, String>{
@Query(nativeQuery = true, 
    value =
"select customer_id, sum(amount) amount,account_id from v_list_charge where customer_id=:customer_id and (date_part('year', current_timestamp) = date_part('year', mois_du_charge)) and date_part('month', mois_du_charge) in (date_part('month', current_timestamp) - 1, date_part('month', current_timestamp) ) group by customer_id, account_id"
    )
    List<VamountDTO> selectCurrentChargeFromView(String customer_id);
    @Query(nativeQuery = true,
    value = 
    "SELECT customer_id, amount,account_id FROM v_list_charge WHERE customer_id=:customer_id and mois_du_charge > date_trunc('month', current_timestamp) + interval '1 month'"
    )
    List<VamountDTO> selectNextChargeFromView(String customer_id);
}
