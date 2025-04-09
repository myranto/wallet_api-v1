package mg.projects.wallet.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.dto.AccounTypeDTO;

/*
 * Création de model account type in local
 */

@Getter
@Setter
@Entity
public class Account_type extends BaseEntity{
    @Column
    private String type;
    @Column
    private String code;
    @Column(name = "creation_date")
    private Timestamp creationdate = new Timestamp(System.currentTimeMillis());

    public Account_type() {
        setDto(AccounTypeDTO.class);
    }

    public Account_type(String type, String code, Timestamp creationdate) {
        this.type = type;
        this.code = code;
        this.creationdate = creationdate;
        setDto(AccounTypeDTO.class);
    }
    
}
