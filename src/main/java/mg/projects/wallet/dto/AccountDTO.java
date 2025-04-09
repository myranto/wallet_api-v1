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
    private BigDecimal currentamount;
    private Timestamp dateamount;
    private String customer_id;
    private String type_id;
    private Timestamp creationdate ;
    private AccounTypeDTO type;
    public AccountDTO() {
        setEntity(Account.class);
    }
    public AccountDTO(BigDecimal currentamount, Timestamp dateamount, String customer_id, String type_id,
            Timestamp creationdate, AccounTypeDTO type) {
        this.currentamount = currentamount;
        this.dateamount = dateamount;
        this.customer_id = customer_id;
        this.type_id = type_id;
        this.creationdate = creationdate;
        this.type = type;
        setEntity(Account.class);
    }

    
}
