package mg.projects.wallet.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column
    private Timestamp creation_date= new Timestamp(System.currentTimeMillis());

    public Transfer() {
        setDto(TransferDTO.class);
    }

    public Transfer(String debit_account, String credit_account, BigDecimal amount, Timestamp start_date,
            Timestamp end_date,
            Timestamp creation_date, String customer) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.creation_date = creation_date;
        this.customer = customer;
        setDto(TransferDTO.class);
    }

}
