package mg.projects.wallet.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account_type account;
    @ManyToOne
    @JoinColumn(name = "type_charge", insertable = false, updatable = false)
    private TypeCharge tcharge;
    @ManyToOne
    @JoinColumn(name = "operation_id", insertable = false, updatable = false)
    private TypeOperation toperation;

    public Charge() {
        setDto(ChargeDTo.class);
    }

    public Charge(String customer_id, Timestamp start_date, Timestamp end_date, BigDecimal amount, String type_charge,
            String account_id, String operation_id,
            Account_type account,TypeCharge tcharge,TypeOperation toperation
    ) {
        this.customer_id = customer_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.type_charge = type_charge;
        this.account_id = account_id;
        this.operation_id = operation_id;
        this.account = account;
        this.tcharge = tcharge;
        this.toperation = toperation;
        setDto(ChargeDTo.class);
    }
    
    
}
