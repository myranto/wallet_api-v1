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
    
    private Timestamp creationdate = new Timestamp(System.currentTimeMillis());

    public TypeOperationDTO() {
        setEntity(TypeOperation.class);
    }

    public TypeOperationDTO(String libelle, String code, Timestamp creationdate) {
        this.libelle = libelle;
        this.code = code;
        this.creationdate = creationdate;
        setEntity(TypeOperation.class);
    }
    
}
