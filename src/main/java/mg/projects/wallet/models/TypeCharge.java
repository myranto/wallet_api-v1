package mg.projects.wallet.models;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.BaseEntity;
import mg.projects.wallet.dto.TypeChargeDTO;

@Getter
@Setter
@Entity(name="type_charge")
public class TypeCharge extends BaseEntity {
    @Column
    private String libelle;
    @Column(nullable = false)
    private String code;
    @Column(name = "creation_date")
    private Timestamp creationdate = new Timestamp(System.currentTimeMillis());

    public TypeCharge() {
        setDto(TypeChargeDTO.class);
    }
    public TypeCharge(String libelle, String code, Timestamp creationdate) {
        this.libelle = libelle;
        this.code = code;
        this.creationdate = creationdate;
        setDto(TypeChargeDTO.class);
    }
    
    

}
