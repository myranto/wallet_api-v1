package mg.projects.wallet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Credit;

@Getter
@Setter
public class CreditDTO extends DTO{
    private String customer_id;
    
    private Timestamp start_date;
    
    private Timestamp end_date;
    
    private BigDecimal amount;
    
    private String account_id;
    
    private String operation_id;
    
    private Timestamp creationdate;
    private AccounTypeDTO account;
    private TypeOperationDTO toperation;

    public CreditDTO() {
        setEntity(Credit.class);
    }

    public CreditDTO(String customer_id, Timestamp start_date, Timestamp end_date, BigDecimal amount, String account_id,
            String operation_id, Timestamp creationdate,
            AccounTypeDTO account,TypeOperationDTO toperation
        ) {
        this.customer_id = customer_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.account_id = account_id;
        this.operation_id = operation_id;
        this.creationdate = creationdate;
        this.account = account;
        this.toperation = toperation;
        setEntity(Credit.class);
    }
    
    
}
