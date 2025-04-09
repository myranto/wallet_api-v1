package mg.projects.wallet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Charge;

@Getter
@Setter
public class ChargeDTo extends DTO{

    private String customer_id;
    
    private Timestamp start_date;
    
    private Timestamp end_date;
    
    private BigDecimal amount;
    
    private String type_charge;
    
    private String account_id;
    
    private String operation_id;
    
    private Timestamp creation_date;
    private AccounTypeDTO account;
    private TypeChargeDTO tcharge;
    private TypeOperationDTO toperation;

    public ChargeDTo() {
        setEntity(Charge.class);
    }

    public ChargeDTo(String customer_id, Timestamp start_date, Timestamp end_date, BigDecimal amount,
            String type_charge, String account_id, String operation_id, Timestamp creation_date,
            AccounTypeDTO account,TypeChargeDTO tcharge,TypeOperationDTO toperation
            ) {
        this.customer_id = customer_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type_charge = type_charge;
        this.account_id = account_id;
        this.operation_id = operation_id;
        this.creation_date = creation_date;
        this.account = account;
        this.tcharge = tcharge;
        this.toperation = toperation;
        setEntity(Charge.class);
    }
    
}
