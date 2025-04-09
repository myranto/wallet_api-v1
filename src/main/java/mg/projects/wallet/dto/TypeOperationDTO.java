package mg.projects.wallet.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import mg.projects.wallet.common.baseModel.DTO;
import mg.projects.wallet.models.TypeOperation;

@Getter
@Setter
public class TypeOperationDTO extends DTO{
    
    private String libelle;
    
    private String code;
    
    public TypeOperationDTO() {
        setEntity(TypeOperation.class);
    }

    public TypeOperationDTO(String libelle, String code) {
        this.libelle = libelle;
        this.code = code;
        setEntity(TypeOperation.class);
    }
    
}
