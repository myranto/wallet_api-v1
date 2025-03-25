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
    private String value;
    @Column(nullable = false)
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());

    public TypeCharge() {
        setDto(TypeChargeDTO.class);
    }
    public TypeCharge(String libelle, String value, Timestamp creation_date) {
        this.libelle = libelle;
        this.value = value;
        this.creation_date = creation_date;
        setDto(TypeChargeDTO.class);
    }
    
    

}
