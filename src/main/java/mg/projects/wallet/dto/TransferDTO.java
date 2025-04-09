package mg.projects.wallet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Transfer;

@Getter
@Setter
public class TransferDTO extends DTO {

    private String debit_account;

    private String credit_account;

    private String customer;

    private BigDecimal amount;

    private Timestamp start_date;

    private Timestamp end_date;

    private Timestamp creation_date;

    public TransferDTO() {
        setEntity(Transfer.class);
    }

    public TransferDTO(String debit_account, String credit_account, BigDecimal amount, Timestamp start_date,
            Timestamp end_date,
            Timestamp creation_date, String customer) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.creation_date = creation_date;
        this.customer = customer;
        setEntity(Transfer.class);
    }

}
