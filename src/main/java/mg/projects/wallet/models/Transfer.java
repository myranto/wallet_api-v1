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
import mg.projects.wallet.dto.TransferDTO;

@Getter
@Setter
@Entity
public class Transfer extends BaseEntity {
    @Column
    private String debit_account;
    @Column
    private String credit_account;
    @Column(nullable = false)
    private String customer;
    @Column
    private BigDecimal amount;
    @Column
    private Timestamp start_date;
    @Column
    private Timestamp end_date;
    @ManyToOne
    @JoinColumn(name = "debit_account", insertable = false, updatable = false)
    private Account_type debit;

    @ManyToOne
    @JoinColumn(name = "credit_account", insertable = false, updatable = false)
    private Account_type credit;

    public Transfer() {
        setDto(TransferDTO.class);
    }

    public Transfer(String debit_account, String credit_account, BigDecimal amount, Timestamp start_date,
            Timestamp end_date,
             String customer,
            Account_type debit, Account_type credit) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.customer = customer;
        this.debit = debit;
        this.credit = credit;
        setDto(TransferDTO.class);
    }

}
