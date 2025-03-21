package mg.projects.wallet.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.BaseEntity;

/*
 * Création de model account type in local
 */

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "account_type")
public class Account_type extends BaseEntity{
    @Column
    private String type;
    @Column
    private String value;
    @Column
    private Timestamp creation_date;

    public Account_type() {
    }
    
}
