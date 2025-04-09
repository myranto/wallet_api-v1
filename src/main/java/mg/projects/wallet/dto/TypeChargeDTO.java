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
    
    private String code;
    
    private Timestamp creationdate = new Timestamp(System.currentTimeMillis());

    public TypeChargeDTO() {
        setEntity(TypeCharge.class);
    }

    public TypeChargeDTO(String libelle, String code, Timestamp creationdate) {
        this.libelle = libelle;
        this.code = code;
        this.creationdate = creationdate;
        setEntity(TypeCharge.class);
    }
    
}
