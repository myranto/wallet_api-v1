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
import mg.projects.wallet.dto.CreditDTO;

@Getter
@Setter
@Entity
public class Credit extends BaseEntity {
    @Column(nullable = false)
    private String customer_id;
    @Column(nullable = false)
    private Timestamp start_date;
    @Column(nullable = true)
    private Timestamp end_date;
    @Column
    private BigDecimal amount;
    @Column
    private String account_id;
    @Column(nullable = false)
    private String operation_id;
    @Column(nullable = false)
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account_type account;

    @ManyToOne
    @JoinColumn(name = "operation_id", insertable = false, updatable = false)
    private TypeOperation toperation;

    public Credit() {
        setDto(CreditDTO.class);
    }

    public Credit(String customer_id, Timestamp start_date, Timestamp end_date, BigDecimal amount, String account_id,
            String operation_id, Timestamp creation_date,
            Account_type account, TypeOperation toperation) {
        this.customer_id = customer_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.amount = amount;
        this.account_id = account_id;
        this.operation_id = operation_id;
        this.creation_date = creation_date;
        this.account = account;
        this.toperation = toperation;
        setDto(CreditDTO.class);
    }

}
