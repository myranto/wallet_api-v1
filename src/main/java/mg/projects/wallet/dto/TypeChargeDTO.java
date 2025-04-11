package mg.projects.wallet.dto;


import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.TypeCharge;

@Getter
@Setter
public class TypeChargeDTO extends DTO{
    private String libelle;
    
    private String code;
    

    public TypeChargeDTO() {
        setEntity(TypeCharge.class);
    }

    public TypeChargeDTO(String libelle, String code) {
        this.libelle = libelle;
        this.code = code;
        setEntity(TypeCharge.class);
    }
    
}
