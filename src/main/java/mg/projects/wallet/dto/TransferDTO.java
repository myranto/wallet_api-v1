package mg.projects.wallet.dto;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.Transfer;

@Getter
@Setter
public class TransferDTO extends DTO{

    private String debit_account;
    
    private String credit_account;
    
    private String amount;
    
    private String start_date;
    
    private String end_date;
    
    private String creation_date;

    public TransferDTO() {
        setEntity(Transfer.class);
    }

    public TransferDTO(String debit_account, String credit_account, String amount, String start_date, String end_date,
            String creation_date) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.creation_date = creation_date;
        setEntity(Transfer.class);
    }

    
    
}
