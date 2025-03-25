package mg.projects.wallet.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.TypeCharge;

@Getter
@Setter
public class TypeChargeDTO extends DTO{
    private String libelle;
    
    private String value;
    
    private Timestamp creation_date = new Timestamp(System.currentTimeMillis());

    public TypeChargeDTO() {
        setEntity(TypeCharge.class);
    }

    public TypeChargeDTO(String libelle, String value, Timestamp creation_date) {
        this.libelle = libelle;
        this.value = value;
        this.creation_date = creation_date;
        setEntity(TypeCharge.class);
    }
    
}
