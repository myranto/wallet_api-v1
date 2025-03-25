package mg.projects.wallet.models;

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
    @Column
    private String amount;
    @Column
    private String start_date;
    @Column
    private String end_date;
    @Column
    private String creation_date;
    
    public Transfer() {
        setDto(TransferDTO.class);
    }
    public Transfer(String debit_account, String credit_account, String amount, String start_date, String end_date,
            String creation_date) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.creation_date = creation_date;
        setDto(TransferDTO.class);
    }

    
}
