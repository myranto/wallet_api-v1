package mg.projects.wallet.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.dto.AccountDTO;

@Entity
@Getter
@Setter
public class Account extends BaseEntity{
    @Column
    private BigDecimal current_amount;
    @Column
    private Timestamp date_amount;
    @Column
    private String customer_id;
    @Column
    private String type_id;
    @Column
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());

    public Account() {
        setDto(AccountDTO.class);
    }
    public Account(BigDecimal current_amount, Timestamp date_amount, String customer_id, String type_id,
            Timestamp creation_date) {
        this.current_amount = current_amount;
        this.date_amount = date_amount;
        this.customer_id = customer_id;
        this.type_id = type_id;
        this.creation_date = creation_date;
        setDto(AccountDTO.class);
    }
    @Override
    public String toString() {
        return "Account [current_amount=" + current_amount + ", date_amount=" + date_amount + ", customer_id="
                + customer_id + ", type_id=" + type_id + ", creation_date=" + creation_date + ", getId()=" + getId()
                + "]";
    }
    
    
}
