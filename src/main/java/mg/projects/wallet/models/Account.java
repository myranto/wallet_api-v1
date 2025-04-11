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
import mg.projects.wallet.dto.AccountDTO;

@Entity
@Getter
@Setter
public class Account extends BaseEntity{
    @Column(name = "current_amount")
    private BigDecimal currentamount;
    @Column(name = "date_amount")
    private Timestamp dateamount;//mila jerena hoe <= date du jour
    @Column(nullable = false)
    private String customer_id;
    @Column(nullable = false)
    private String type_id;
    // partie récupération de donnée uniquement
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private Account_type type;

    public Account() {
        setDto(AccountDTO.class);
    }
    public Account(BigDecimal currentamount, Timestamp dateamount, String customer_id, String type_id,
            Account_type type) {
        this.currentamount = currentamount;
        this.dateamount = dateamount;
        this.customer_id = customer_id;
        this.type_id = type_id;
        this.type = type;
        setDto(AccountDTO.class);
    }
    @Override
    public String toString() {
        return "Account [currentamount=" + currentamount + ", dateamount=" + dateamount + ", customer_id="
                + customer_id + ", type_id=" + type_id + ", getId()=" + getId()
                + "]";
    }
    
    
}
