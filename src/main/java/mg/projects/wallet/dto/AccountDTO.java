package mg.projects.wallet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Account;

@Getter
@Setter
public class AccountDTO extends DTO {
    private BigDecimal current_amount;
    private Timestamp date_amount;
    private String customer_id;
    private String type_id;
    private Timestamp creation_date ;

    public AccountDTO() {
        setEntity(Account.class);
    }
    public AccountDTO(BigDecimal current_amount, Timestamp date_amount, String customer_id, String type_id,
            Timestamp creation_date) {
        this.current_amount = current_amount;
        this.date_amount = date_amount;
        this.customer_id = customer_id;
        this.type_id = type_id;
        this.creation_date = creation_date;
        setEntity(Account.class);
    }

    
}
