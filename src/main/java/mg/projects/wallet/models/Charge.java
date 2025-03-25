package mg.projects.wallet.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.dto.ChargeDTo;

@Getter
@Setter
@Entity
public class Charge extends BaseEntity {
    @Column(nullable = false)
    private String customer_id;
    @Column(nullable = false)
    private Timestamp start_date;
    @Column(nullable = true)
    private Timestamp end_date;
    @Column
    private BigDecimal amount;
    @Column
    private String type_charge;
    @Column
    private String account_id;
    @Column(nullable = false)
    private String operation_id;
    @Column(nullable = false)
    private Timestamp creation_date= new Timestamp(System.currentTimeMillis());

    public Charge() {
        setDto(ChargeDTo.class);
    }
    public Charge(String customer_id, Timestamp start_date, Timestamp end_date, BigDecimal amount, String type_charge,
            String account_id, String operation_id, Timestamp creation_date) {
        this.customer_id = customer_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type_charge = type_charge;
        this.account_id = account_id;
        this.operation_id = operation_id;
        this.creation_date = creation_date;
        setDto(ChargeDTo.class);
    }
    
    
}
