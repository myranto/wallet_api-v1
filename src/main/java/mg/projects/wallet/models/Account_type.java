package mg.projects.wallet.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.BaseEntity;
import mg.projects.wallet.dto.AccounTypeDTO;

/*
 * Création de model account type in local
 */

@Getter
@Setter
@Entity(name = "account_type")
public class Account_type extends BaseEntity{
    @Column
    private String type;
    @Column
    private String value;
    @Column
    private Timestamp creation_date;

    public Account_type() {
        setDto(AccounTypeDTO.class);
    }

    public Account_type(String type, String value, Timestamp creation_date) {
        this.type = type;
        this.value = value;
        this.creation_date = creation_date;
        setDto(AccounTypeDTO.class);
    }
    
}
